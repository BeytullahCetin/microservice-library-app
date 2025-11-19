package com.turkcell.fine_service.service.support;

import com.turkcell.fine_service.client.BorrowClient;
import com.turkcell.fine_service.config.FineServiceProperties;
import com.turkcell.fine_service.exception.BusinessException;
import feign.FeignException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoteReferenceValidator {

	private final FineServiceProperties properties;
	private final BorrowClient borrowClient;

	public void assertBorrowExists(UUID borrowId) {
		invokeSafely(properties.getRemoteValidation().isEnabled(), () -> borrowClient.getBorrow(borrowId),
				"BORROW_NOT_FOUND", "Borrow record not found: " + borrowId);
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

