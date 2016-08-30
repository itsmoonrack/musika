package io.musika.notifier.domain.model.release;

import io.musika.notifier.domain.model.TrackId;

/**
 * Thrown when trying to register an event with an unknown track id.
 */
public class UnknownTrackException extends UnableToCreateReleaseEventException {

	private final TrackId trackId;

	/**
	 * @param trackId track id
	 */
	public UnknownTrackException(final TrackId trackId) {
		this.trackId = trackId;
	}

	@Override
	public String getMessage() {
		return "No track with id " + trackId + " exists in the system";
	}

}
