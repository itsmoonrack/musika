package io.musika.notifier.domain.model.release;

import io.musika.notifier.domain.model.shared.kernel.ReleaseNumber;

/**
 * Thrown when trying to register an event with an unknown release number.
 */
public class UnknownReleaseException extends UnableToCreateReleaseEventException {

	private final ReleaseNumber releaseNumber;

	public UnknownReleaseException(final ReleaseNumber releaseNumber) {
		this.releaseNumber = releaseNumber;
	}

	@Override
	public String getMessage() {
		return "No release with number " + releaseNumber + " exists in the system";
	}

}
