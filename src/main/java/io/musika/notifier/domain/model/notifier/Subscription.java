package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.shared.Entity;
import io.musika.notifier.domain.model.shared.kernel.TrackId;

/**
 * A Subscription. This is the central class in the domain model, and it is
 * the root of the Subscription-Release-Track-Notification-SendSpecification aggregate.
 * TODO doc
 */
public class Subscription implements Entity<Subscription, TrackId> {

	private TrackId trackId;
	private Subscribers subscribers;
	private Availability availability;
	private Catalog releases;

	public Subscription(final TrackId trackId, final SendSpecification sendSpecification) {
		this.trackId = trackId;
		this.sendSpecification = sendSpecification;
	}

	public void notifySubscribers(final ReleaseHistory releaseHistory) {

	}

	@Override
	public boolean sameIdentityAs(Subscription other) {
		return false;
	}

	@Override
	public TrackId identity() {
		return trackId;
	}

	Subscription() {
		// Needed by hibernate
	}

	// Auto-generated surrogate key
	private Long id;

}
