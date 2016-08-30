package io.musika.notifier.application;

import java.net.URI;
import java.util.List;

import io.musika.notifier.domain.model.Release;
import io.musika.notifier.domain.model.TrackId;

/**
 * Track notifier service.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public interface NotifierService {

	/**
	 * Registers a new track in the notification system, not yet subscribed.
	 *
	 * @param store	track store to subscribe
	 * @param name	track name
	 * @return Track id
	 */
	TrackId addNewTrack(URI store, String name);

	/**
	 * Requests a list of releases for this track.
	 *
	 * @param trackId track id
	 * @return A list of possible releases for this track
	 */
	List<Release> requestReleasesForTrack(TrackId trackId);



}
