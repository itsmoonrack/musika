package io.musika.notifier.application;

import java.net.URI;
import java.util.Date;

import io.musika.notifier.domain.model.ReleaseNumber;
import io.musika.notifier.domain.model.TrackId;
import io.musika.notifier.domain.model.release.ReleaseEvent;

/**
 * Release event service.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public interface ReleaseEventService {

	/**
	 * Registers a release event in the system, and notifies interested
	 * parties that a track has been released.
	 *
	 * @param completionTime when the event was completed
	 * @param trackId track id
	 * @param releaseNumber release number
	 * @param uriCode URI of the store
	 * @param type type of event
	 */
	void registerReleaseEvent(Date completionTime /* TODO: Check Java 8 date type to use. */,
							  TrackId trackId,
							  ReleaseNumber releaseNumber,
							  URI uriCode,
							  ReleaseEvent.Type type);

}
