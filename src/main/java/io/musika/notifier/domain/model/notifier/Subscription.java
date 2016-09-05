package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.shared.Entity;
import io.musika.notifier.domain.model.shared.kernel.Release;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.shared.kernel.UserId;

/**
 * A Subscription. This is the central class in the domain model, and it is
 * the root of the ...
 * TODO doc
 */
public class Subscription implements Entity<Subscription, TrackId> {

	private UserId userId;
	private TrackSpecification trackSpecification;
	private Notification notification;
	private Release release;

	public Subscription(final UserId userId, final TrackSpecification trackSpecification) {
		this.userId = userId;
		this.trackSpecification = trackSpecification;
	}

	public void notifySubscribers(final ReleaseHistory releaseHistory) {

	}

	@Override
	public boolean sameIdentityAs(Subscription other) {
		return false;
	}

	@Override
	public TrackId identity() {
		return null;
	}

}
