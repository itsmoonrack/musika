package io.musika.notifier.domain.model.notifier;

import static io.musika.notifier.domain.model.notifier.SendingStatus.MISSENT;
import static io.musika.notifier.domain.model.notifier.SendingStatus.NOT_SENT;
import static io.musika.notifier.domain.model.notifier.SendingStatus.SENT;
import static io.musika.notifier.domain.model.notifier.Availability.AVAILABLE;
import static io.musika.notifier.domain.model.notifier.Availability.NOT_AVAILABLE;
import static io.musika.notifier.domain.model.notifier.Availability.UNKNOWN;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.Date;

import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.Release;
import io.musika.notifier.domain.model.shared.kernel.Track;
import io.musika.notifier.domain.model.store.Store;

/**
 * The actual notification of the subscription, as opposed to
 * the user requirement (SendSpecification) and the release (Release).
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
	 * @param sendSpecification track specification
	 * @param release release
	 * @return An up to date notification
	 */
	Notification updateOnRelease(final SendSpecification sendSpecification, final Release release) {
		notNull(sendSpecification, "Track specification is null");

		return new Notification(this.lastEvent, release, sendSpecification);
	}

	/**
	 * Creates a new notification based on the complete release history of a subscription,
	 * as well as its track specification and itinerary.
	 *
	 * @param sendSpecification track specification
	 * @param release release
	 * @param releaseHistory release history
	 * @return An up to date notification
	 */
	static Notification derivedFrom(final SendSpecification sendSpecification, final Release release, final ReleaseHistory releaseHistory) {
		notNull(sendSpecification, "Track specification is null");
		notNull(releaseHistory, "Release history is null");

		final ReleaseEvent lastEvent = releaseHistory.mostRecentlyReleasedEvent();

		return new Notification(lastEvent, release, sendSpecification);
	}

	/**
	 * Internal constructor.
	 *
	 * @param lastEvent last event
	 * @param release release
	 * @param sendSpecification track specification
	 */
	private Notification(final ReleaseEvent lastEvent, final Release release, final SendSpecification sendSpecification) {
		this.calculatedAt = new Date();
		this.lastEvent = lastEvent;

		this.missent = calculateMissentStatus(release);
		this.sendingStatus = calculateSendingStatus(release, sendSpecification);
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

	private boolean calculateMissentStatus(Release release) {
		if (lastEvent == null) {
			return false;
		} else {
			return !release.isExpected(lastEvent);
		}
	}

	private SendingStatus calculateSendingStatus(final Release release, final SendSpecification sendSpecification) {
		if (release == null) {
			return NOT_SENT;
		} else {
			if (sendSpecification.isSatisfiedBy(release)) {
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
