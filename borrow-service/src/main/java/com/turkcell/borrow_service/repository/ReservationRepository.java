package com.turkcell.borrow_service.repository;

import com.turkcell.borrow_service.entity.Reservation;
import com.turkcell.borrow_service.entity.enums.ReservationStatus;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

	List<Reservation> findByCustomerId(UUID customerId);

	List<Reservation> findByBookIdAndStatusOrderByExpireAtAsc(UUID bookId, ReservationStatus status);

	boolean existsByCustomerIdAndBookIdAndStatusIn(UUID customerId, UUID bookId, Collection<ReservationStatus> statuses);

	List<Reservation> findByStatusAndExpireAtBefore(ReservationStatus status, LocalDateTime dateTime);
}

