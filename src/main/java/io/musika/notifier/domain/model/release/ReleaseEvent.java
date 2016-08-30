package io.musika.notifier.domain.model.release;

import java.util.Date;

import io.musika.notifier.domain.model.Release;
import io.musika.notifier.domain.model.Track;
import io.musika.notifier.domain.model.store.Store;

/**
 * A ReleaseEvent is used to register the event when, for instance, a release is available from a store at a given time.
 * </p>
 * ReleaseEvents are sent from different Store Connectors some time after the event occurred and contain information about the store,
 * timestamp of the event, and possibly, if applicable a {@link Release}.
 * </p>
 * The class is the only member, and consequently the root, of the ReleaseEvent aggregate.
 * </p>
 * ReleaseEvent's could contain information about a {@link Release} and if so,
 * the event must be either {@link Type#RELEASED} or {@link Type#REMOVED}.
 * </p>
 * All other events must be of {@link Type#SUBSCRIBED}.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public final class ReleaseEvent {

	private Type type;
	private Store store;
	private Release release;
	private Date eventTime;
	private Track track;

	/**
	 * Release event type.
	 */
	public enum Type {
		RELEASED(true),
		REMOVED(true),
		SUBSCRIBED(false);

		private final boolean releaseRequired;

		/**
		 * Private enum constructor.
		 *
		 * @param releaseRequired whether or not a release is associated with this event type
		 */
		Type(final boolean releaseRequired) {
			this.releaseRequired = releaseRequired;
		}

		public boolean requiresRelease() {
			return releaseRequired;
		}

	}

	/**
	 * @param track				track
	 * @param eventTime			event time
	 * @param type              type of event
	 * @param store  			where the event took place
	 */
	public ReleaseEvent(final Track track,
					    final Date eventTime,
						final Type type,
						final Store store) {
		this(track, eventTime, type, store, null);
	}

	/**
	 * @param track				track
	 * @param eventTime			event time
	 * @param type              type of event
	 * @param store  			where the event took place
	 * @param release			the release
	 */
	public ReleaseEvent(final Track track,
						final Date eventTime,
						final Type type,
						final Store store,
						final Release release) {

		if (type.requiresRelease()) {
			throw new IllegalArgumentException("Release is required for event type " + type);
		}

		this.release = release;
		this.eventTime = eventTime;
		this.type = type;
		this.store = store;
		this.track = track;
	}

}
