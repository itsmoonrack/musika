package io.musika.notifier.domain.model.release;

import java.net.URI;

public class UnknownStoreException extends UnableToCreateReleaseEventException {

	private final URI uriCode;

	public UnknownStoreException(final URI uriCode) {
		this.uriCode = uriCode;
	}

	@Override
	public String getMessage() {
		return "No store with URI code " + uriCode + " exists in the system";
	}

}
