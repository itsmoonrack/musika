package io.musika.notifier.domain.model.notifier;

import static io.musika.notifier.domain.model.notifier.Availability.AVAILABLE;
import static io.musika.notifier.domain.model.notifier.Availability.NOT_AVAILABLE;
import static io.musika.notifier.domain.model.notifier.Availability.UNKNOWN;
import static io.musika.notifier.domain.model.notifier.NotificationStatus.CHANGED;
import static io.musika.notifier.domain.model.notifier.NotificationStatus.NOT_SENT;
import static io.musika.notifier.domain.model.notifier.NotificationStatus.RELEASED;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.Date;

import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.Release;
import io.musika.notifier.domain.model.store.Store;

/**
 * The actual notification of the subscription, as opposed to
 * the user requirement (TrackSpecification) and the release (Release).
 */
public class Notification implements ValueObject<Notification> {

	private Availability availability;
	private boolean available;
	private Date calculatedAt;
	private Release currentRelease;
	private Store lastStore;
	private ReleaseEvent lastEvent;
	private NotificationStatus notificationStatus;

	/**
	 * Creates a new notification to reflect changes in fetching, i.e.
	 * when the track specification or the catalog has changed but no
	 * additional release event of the track has been performed.
	 *
	 * @param trackSpecification track specification
	 * @param catalog catalog
	 * @return An up to date notification
	 */
	Notification updateOnFetching(final TrackSpecification trackSpecification,
                                  final Catalog catalog) {
		notNull(trackSpecification, "Track specification is null");

		return new Notification(this.lastEvent, catalog, trackSpecification);
	}

	/**
	 * Creates a new notification based on the complete release history of a subscription,
	 * as well as its track specification and catalog.
	 *
	 * @param trackSpecification track specification
	 * @param catalog catalog
	 * @param releaseHistory release history
	 * @return An up to date notification
	 */
	static Notification derivedFrom(final TrackSpecification trackSpecification,
									final Catalog catalog,
									final ReleaseHistory releaseHistory) {
		notNull(trackSpecification, "Track specification is null");
		notNull(releaseHistory, "Release history is null");

		final ReleaseEvent lastReleasedEvent = releaseHistory.mostRecentlyReleasedEvent();

		return new Notification(lastReleasedEvent, catalog, trackSpecification);
	}

	/**
	 * Internal constructor.
	 *
	 * @param lastEvent last released event
	 * @param catalog release
	 * @param trackSpecification track specification
	 */
	private Notification(final ReleaseEvent lastEvent,
                         final Catalog catalog,
                         final TrackSpecification trackSpecification) {
		this.calculatedAt = new Date();
		this.lastEvent = lastEvent;

		this.available = calculateAvailabilityStatus(catalog);
		this.notificationStatus = calculateNotificationStatus(catalog, trackSpecification);
		this.availability = calculateAvailability();
		this.lastStore = calculateLastReleasedStore();
		this.currentRelease = calculateCurrentRelease();
	}

	/**
	 * @return Availability
	 */
	public Availability availability() {
		return availability;
	}

	/**
	 * @return Last store where the track has been released, or Store.UNKNOWN if the release history is empty.
	 */
	public Store lastStore() {
		return defaultIfNull(lastStore, Store.UNKNOWN);
	}

	/**
	 * @return Current release.
	 */
	public Release currentRelease() {
		return defaultIfNull(currentRelease, Release.NONE);
	}

	/**
	 * Check if track is available.
	 * @return <code>true</code> if the track is available.
	 */
	public boolean isAvailable() {
		return available;
	}

	private Availability calculateAvailability() {
		if (lastEvent == null) {
			return UNKNOWN;
		}

		switch (lastEvent.type()) {
			case REMOVED:
				return NOT_AVAILABLE;
			case RELEASED:
				return AVAILABLE;
			default:
				return UNKNOWN;
		}
	}

	private Store calculateLastReleasedStore() {
		if (lastEvent != null) {
			return lastEvent.store();
		} else {
			return null;
		}
	}

	private Release calculateCurrentRelease() {
		if (lastEvent != null) {
			return lastEvent.release();
		} else {
			return null;
		}
	}

	private boolean calculateAvailabilityStatus(final Catalog catalog) {
		if (lastEvent == null) {
			return false;
		} else {
			return !catalog.isExpected(lastEvent);
		}
	}

	private NotificationStatus calculateNotificationStatus(final Catalog catalog, final TrackSpecification trackSpecification) {
		if (catalog == null) {
			return NOT_SENT;
		} else {
			if (trackSpecification.isSatisfiedBy(catalog)) {
				return RELEASED;
			} else {
				return CHANGED;
			}
		}
	}

	@Override
	public boolean sameValueAs(Notification other) {
		return false;
	}

	protected Notification() {
		// Needed by Hibernate
	}

}
