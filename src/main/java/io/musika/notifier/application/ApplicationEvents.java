package io.musika.notifier.application;

import io.musika.notifier.domain.model.notifier.Subscription;
import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.interfaces.notification.ReleaseEventRegistrationAttempt;

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
	 * A subscription has been removed.
	 *
	 * @param subscription subscription
	 */
	void subscriptionWasRemoved(Subscription subscription);

	/**
	 * A subscription has been released.
	 *
	 * @param subscription subscription
	 */
	void subscriptionWasCreated(Subscription subscription);

	/**
	 * A notification event registration attempt is received.
	 *
	 * @param attempt
	 */
	void receivedReleaseEventRegistrationAttempt(ReleaseEventRegistrationAttempt attempt);

}
