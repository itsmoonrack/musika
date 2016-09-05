package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.Track;
import io.musika.notifier.domain.model.store.Store;

import java.util.Date;

import static io.musika.notifier.domain.model.notifier.Availability.*;
import static io.musika.notifier.domain.model.notifier.SendingStatus.*;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * The actual notification of the subscription, as opposed to
 * the user requirement (TrackSpecification) and the release (Release).
 */
public class Notification implements ValueObject<Notification> {

	private Availability availability;
	private Store lastKnownStore;
	private Track currentTrack;
	private boolean missent;
	private SendingStatus sendingStatus;
	private Date calculatedAt;
	private ReleaseEvent lastEvent;

	/**
	 * Creates a new notification to reflect changes in sending, i.e.
	 * when the track specification or the release has changed but no
	 * additional release event of the track has been performed.
	 *
	 * @param trackSpecification track specification
	 * @param catalog catalog
	 * @return An up to date notification
	 */
	Notification updateOnSending(final TrackSpecification trackSpecification,
                                 final Catalog catalog) {
		notNull(trackSpecification, "Track specification is null");

		return new Notification(this.lastEvent, catalog, trackSpecification);
	}

	/**
	 * Creates a new notification based on the complete release history of a subscription,
	 * as well as its track specification and itinerary.
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

		final ReleaseEvent lastEvent = releaseHistory.mostRecentlyReleasedEvent();

		return new Notification(lastEvent, catalog, trackSpecification);
	}

	/**
	 * Internal constructor.
	 *
	 * @param lastEvent last event
	 * @param catalog release
	 * @param trackSpecification track specification
	 */
	private Notification(final ReleaseEvent lastEvent,
                         final Catalog catalog,
                         final TrackSpecification trackSpecification) {
		this.calculatedAt = new Date();
		this.lastEvent = lastEvent;

		this.missent = calculateMissentStatus(catalog);
		this.sendingStatus = calculateSendingStatus(catalog, trackSpecification);
		this.availability = calculateVacantStatus();
		this.lastKnownStore = calculateLastKnownStore();
		this.currentTrack = calculateCurrentTrack();
	}

	private Availability calculateVacantStatus() {
		if (lastEvent == null) {
			return UNKNOWN;
		}

		switch (lastEvent.type()) {
			case SUBSCRIBED:
			case REMOVED:
				return NOT_AVAILABLE;
			case RELEASED:
				return AVAILABLE;
			default:
				return UNKNOWN;
		}
	}

	private Store calculateLastKnownStore() {
		if (lastEvent != null) {
			return lastEvent.store();
		} else {
			return null;
		}
	}

	private Track calculateCurrentTrack() {
		if (lastEvent != null && availability.sameValueAs(AVAILABLE)) {
			return lastEvent.track();
		} else {
			return null;
		}
	}

	private boolean calculateMissentStatus(final Catalog catalog) {
		if (lastEvent == null) {
			return false;
		} else {
			return !catalog.isExpected(lastEvent);
		}
	}

	private SendingStatus calculateSendingStatus(final Catalog catalog, final TrackSpecification trackSpecification) {
		if (catalog == null) {
			return NOT_SENT;
		} else {
			if (trackSpecification.isSatisfiedBy(catalog)) {
				return SENT;
			} else {
				return MISSENT;
			}
		}
	}

	@Override
	public boolean sameValueAs(Notification other) {
		return false;
	}

}
