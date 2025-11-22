package com.turkcell.borrow_service.service.impl;

import com.turkcell.borrow_service.dto.request.CreateReservationRequest;
import com.turkcell.borrow_service.dto.request.UpdateReservationStatusRequest;
import com.turkcell.borrow_service.dto.response.ReservationResponse;
import com.turkcell.borrow_service.entity.Reservation;
import com.turkcell.borrow_service.entity.enums.ReservationStatus;
import com.turkcell.borrow_service.exception.BusinessException;
import com.turkcell.borrow_service.exception.NotFoundException;
import com.turkcell.borrow_service.mapper.ReservationMapper;
import com.turkcell.borrow_service.repository.ReservationRepository;
import com.turkcell.borrow_service.service.ReservationService;
import com.turkcell.borrow_service.service.support.RemoteReferenceValidator;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private static final EnumSet<ReservationStatus> MUTABLE_STATUSES = EnumSet.of(ReservationStatus.ACTIVE);

	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;
	private final RemoteReferenceValidator referenceValidator;

	@Override
	@Transactional
	public ReservationResponse createReservation(CreateReservationRequest request) {
		if (request.expireAt().isBefore(LocalDateTime.now())) {
			throw new BusinessException("INVALID_EXPIRATION", "Reservation expiration must be in the future");
		}

		referenceValidator.assertCustomerExists(request.customerId());
		referenceValidator.assertBookExists(request.bookId());

		boolean alreadyReserved = reservationRepository.existsByCustomerIdAndBookIdAndStatusIn(
				request.customerId(),
				request.bookId(),
				MUTABLE_STATUSES);
		if (alreadyReserved) {
			throw new BusinessException("RESERVATION_EXISTS", "Active reservation already exists for this user/book");
		}

		Reservation reservation = reservationMapper.toReservation(request);
		Reservation saved = reservationRepository.save(reservation);
		return reservationMapper.toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public ReservationResponse getReservation(UUID id) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("RESERVATION_NOT_FOUND", "Reservation not found"));
		return reservationMapper.toResponse(reservation);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservationResponse> getCustomerReservations(UUID customerId) {
		return reservationRepository.findByCustomerId(customerId)
				.stream()
				.map(reservationMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservationResponse> getActiveReservationsForBook(UUID bookId) {
		return reservationRepository.findByBookIdAndStatusOrderByExpireAtAsc(bookId, ReservationStatus.ACTIVE)
				.stream()
				.map(reservationMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional
	public ReservationResponse updateStatus(UUID reservationId, UpdateReservationStatusRequest request) {
		Reservation reservation = reservationRepository.findById(Objects.requireNonNull(reservationId))
				.orElseThrow(() -> new NotFoundException("RESERVATION_NOT_FOUND", "Reservation not found"));

		ReservationStatus currentStatus = reservation.getStatus();
		ReservationStatus newStatus = request.status();

		if (currentStatus == newStatus) {
			return reservationMapper.toResponse(reservation);
		}

		if (!MUTABLE_STATUSES.contains(currentStatus)) {
			throw new BusinessException("RESERVATION_IMMUTABLE", "Reservation can no longer change status");
		}

		if (currentStatus == ReservationStatus.ACTIVE
				&& !(newStatus == ReservationStatus.FULFILLED || newStatus == ReservationStatus.CANCELLED
						|| newStatus == ReservationStatus.EXPIRED)) {
			throw new BusinessException("INVALID_STATUS_TRANSITION", "Unsupported reservation status transition");
		}

		reservation.setStatus(newStatus);
		return reservationMapper.toResponse(reservationRepository.save(reservation));
	}

	@Override
	@Transactional
	public int expireReservations() {
		List<Reservation> overdueReservations = reservationRepository
				.findByStatusAndExpireAtBefore(ReservationStatus.ACTIVE, LocalDateTime.now());
		overdueReservations.forEach(reservation -> reservation.setStatus(ReservationStatus.EXPIRED));
		reservationRepository.saveAll(overdueReservations);
		return overdueReservations.size();
	}

	@Scheduled(cron = "0 * * * * *")
	public void scheduledExpirationJob() {
		expireReservations();
	}
}

