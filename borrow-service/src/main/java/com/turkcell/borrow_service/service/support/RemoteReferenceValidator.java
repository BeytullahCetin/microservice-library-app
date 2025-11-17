package com.turkcell.borrow_service.service.support;

import com.turkcell.borrow_service.client.BookClient;
import com.turkcell.borrow_service.client.BookCopyClient;
import com.turkcell.borrow_service.client.CustomerClient;
import com.turkcell.borrow_service.config.BorrowServiceProperties;
import com.turkcell.borrow_service.exception.BusinessException;
import feign.FeignException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoteReferenceValidator {

	private final BorrowServiceProperties properties;
	private final CustomerClient customerClient;
	private final BookCopyClient bookCopyClient;
	private final BookClient bookClient;

	public void assertCustomerExists(UUID customerId) {
		invokeSafely(properties.getRemoteValidation().isEnabled(), () -> customerClient.getCustomer(customerId),
				"CUSTOMER_NOT_FOUND", "Customer not found: " + customerId);
	}

	public void assertBookCopyExists(UUID bookCopyId) {
		invokeSafely(properties.getRemoteValidation().isEnabled(), () -> bookCopyClient.getBookCopy(bookCopyId),
				"BOOK_COPY_NOT_FOUND", "Book copy not found: " + bookCopyId);
	}

	public void assertBookExists(UUID bookId) {
		invokeSafely(properties.getRemoteValidation().isEnabled(), () -> bookClient.getBook(bookId),
				"BOOK_NOT_FOUND", "Book not found: " + bookId);
	}

	private void invokeSafely(boolean validationEnabled, Runnable operation, String code, String message) {
		if (!validationEnabled) {
			return;
		}
		try {
			operation.run();
		} catch (FeignException.NotFound exception) {
			throw new BusinessException(code, message);
		} catch (FeignException exception) {
			throw new BusinessException("REMOTE_SERVICE_ERROR", exception.getMessage());
		}
	}
}

