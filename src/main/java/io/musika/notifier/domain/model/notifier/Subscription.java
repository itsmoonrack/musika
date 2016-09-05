package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.shared.Entity;
import io.musika.notifier.domain.model.shared.kernel.TrackId;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * A Subscription. This is the central class in the domain model, and it is
 * the root of the Subscription-Subscribers-Availability-Catalog aggregate.
 * TODO doc
 */
public class Subscription implements Entity<Subscription, TrackId> {

	private TrackId trackId;
	private TrackSpecification trackSpecification;
	private Catalog catalog;
    private Notification notification;

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
     * Specifies a new alert for this subscription.
     *
     * @param trackSpecification send specification.
     */
	public void specifyNewAlert(final TrackSpecification trackSpecification) {
	    notNull(trackSpecification, "Send specification is null");

        this.trackSpecification = trackSpecification;
        // Handling consistency within the Subscription aggregate synchronously
        this.notification = notification.updateOnSending(this.trackSpecification, this.catalog);
    }

    /**
     * Attach a new catalog to this subscription.
     *
     * @param catalog a catalog. May not be null.
     */
	public void assignToCatalog(final Catalog catalog) {
	    notNull(catalog, "Catalog is null");

	    this.catalog = catalog;
        // Handling consistency within the Subscription aggregate synchronously
        this.notification = notification.updateOnSending(this.trackSpecification, this.catalog);
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

	Subscription() {
		// Needed by hibernate
	}

	// Auto-generated surrogate key
	private Long id;

}
