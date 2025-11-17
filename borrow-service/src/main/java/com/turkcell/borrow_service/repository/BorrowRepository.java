package com.turkcell.borrow_service.repository;

import com.turkcell.borrow_service.entity.Borrow;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

	boolean existsByBookCopyIdAndReturnDateIsNull(UUID bookCopyId);

	Optional<Borrow> findFirstByBookCopyIdAndReturnDateIsNull(UUID bookCopyId);

	List<Borrow> findByReturnDateIsNull();
}

