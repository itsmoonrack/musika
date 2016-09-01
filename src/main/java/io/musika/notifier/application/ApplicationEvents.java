package io.musika.notifier.application;

import io.musika.notifier.domain.model.notifier.Track;
import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.presentation.notification.ReleaseEventRegistrationAttempt;

/**
 * This interface provides a way to let other parts of the system know about events that have occurred.
 * </p>
 * It may be implemented synchronously or asynchronously, using JMS for example.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public interface ApplicationEvents {

	/**
	 * A track has been released.
	 *
	 * @param event release event
	 */
	void trackWasReleased(ReleaseEvent event);

	/**
	 * A track has been removed.
	 *
	 * @param track track
	 */
	void trackWasRemoved(Track track);

	/**
	 * A track has been released.
	 *
	 * @param track track
	 */
	void trackWasAvailable(Track track);

	/**
	 * A notification event registration attempt is received.
	 *
	 * @param attempt
	 */
	void receivedReleaseEventRegistrationAttempt(ReleaseEventRegistrationAttempt attempt);

}
