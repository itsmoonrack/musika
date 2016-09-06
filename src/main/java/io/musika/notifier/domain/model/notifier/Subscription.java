package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.shared.Entity;
import io.musika.notifier.domain.model.shared.kernel.TrackId;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * A Subscription. This is the central class in the domain model, and it is
 * the root of the Subscription-Catalog-Record-Notification-TrackSpecification aggregate.
 *
 * A subscription is identified by a unique track id, and it always has a track specification.
 * The life cycle of a subscription begins with the tracking procedure, when the track id is
 * assigned. During a (short) period of time, between tracking and initial release of a record,
 * the subscription has no catalog.
 *
 * TODO:
 * The subscriber requests a list of possible track, matching the track specification,
 * and assigns the subscription to one track
 *
 * When a track is released, the status of the notification changes. Everything about the
 * notification of the subscription is contained in the Notification value object, which
 * is replaced whenever a track is released by an asynchronous event triggered by the
 * registration of the release event.
 *
 * The notification can also be affected by identification changes, i.e. when the track
 * specification changes, or the subscription is attached to a new catalog. In that case,
 * the notification update is performed synchronously within the subscription aggregate.
 *
 * The life cycle of a subscription ends when TODO
 *
 * The subscription aggregate, and the entire domain model, is built to solve the problem
 * of tracking release. All important
 */
public class Subscription implements Entity<Subscription, TrackId> {

	private TrackId trackId;
	private Catalog catalog;
    private Notification notification;
	private TrackSpecification trackSpecification;
    private int followers = 0;

	public Subscription(final TrackId trackId, final TrackSpecification trackSpecification) {
		notNull(trackId, "Track ID is null");
        notNull(trackSpecification, "Send specification is null");

		this.trackId = trackId;
        this.trackSpecification = trackSpecification;

		this.notification = Notification.derivedFrom(
		        this.trackSpecification, this.catalog, ReleaseHistory.EMPTY
        );
	}

    /**
     * The track id of this entity.
     * @return Track id.
     */
    @Override
    public TrackId identity() {
        return trackId;
    }

    /**
     * @return The notification. Never null.
     */
    public Notification notification() {
        return notification;
    }

    /**
     * @return The followers counts.
     */
    public int followers() {
        return followers;
    }

    /**
     * Specifies a new track for this subscription.
     *
     * @param trackSpecification track specification.
     */
	public void specifyNewTrack(final TrackSpecification trackSpecification) {
	    notNull(trackSpecification, "Send specification is null");

        this.trackSpecification = trackSpecification;
        // Handling consistency within the Subscription aggregate synchronously
        this.notification = notification.updateOnFetching(this.trackSpecification, this.catalog);
    }

    /**
     * Attach a new catalog to this subscription.
     *
     * @param catalog a catalog. May not be null.
     */
	public void attachToCatalog(final Catalog catalog) {
	    notNull(catalog, "Catalog is null");

	    this.catalog = catalog;
        // Handling consistency within the Subscription aggregate synchronously
        this.notification = notification.updateOnFetching(this.trackSpecification, this.catalog);
    }

    /**
     * Updates all aspects of the subscription aggregate status based on the
     * current send specification, catalog and releases of the subscription.
     * <p/>
     * When either of those three changes, i.e. when a new alert is specified
     * for the subscription, the subscription is assigned to a catalog or when
     * the subscription has a release event, the status must be re-calculated.
     * <p/>
     * {@link TrackSpecification} and {@link Catalog} are both inside the Subscription
     * aggregate, so changes to them cause the status to be updated <b>synchronously</b>,
     * but changes to the notification history (when a subscription has new releases)
     * cause the status update to happen <b>asynchronously</b> since {@link ReleaseEvent}
     * is in different aggregate.
     *
     * @param releaseHistory release history
     */
	public void deriveNotificationStatus(final ReleaseHistory releaseHistory) {
        // Notification is a value object, so we can simply discard the old one
        // and replace it with a new
        this.notification = Notification.derivedFrom(this.trackSpecification, this.catalog, releaseHistory);
	}

    /**
     * @param object to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(Subscription)
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        final Subscription other = (Subscription) object;

        return sameIdentityAs(other);
    }

	@Override
	public boolean sameIdentityAs(final Subscription other) {
		return other != null && trackId.sameValueAs(other.trackId);
	}

	@Override
    public int hashCode() {
	    return trackId.hashCode();
    }

	protected Subscription() {
		// Needed by hibernate
	}

	// Auto-generated surrogate key
	private Long id;

}
