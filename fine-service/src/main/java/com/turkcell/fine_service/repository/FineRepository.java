package com.turkcell.fine_service.repository;

import com.turkcell.fine_service.entity.Fine;
import com.turkcell.fine_service.entity.enums.FineType;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, UUID> {

	List<Fine> findByBorrowIdOrderByIssueDateDesc(UUID borrowId);

	List<Fine> findByPaidFalseOrderByIssueDateAsc();

	boolean existsByBorrowIdAndFineTypeAndPaidFalse(UUID borrowId, FineType fineType);
}

