package io.musika.notifier.domain.model.release;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.Date;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import io.musika.notifier.domain.model.shared.DomainEvent;
import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.track.Track;
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
public final class ReleaseEvent implements DomainEvent<ReleaseEvent> {

	private Type type;
	private Store store;
	private Release release;
	private Date eventTime;
	private Track track;

	/**
	 * Release event type.
	 */
	public enum Type implements ValueObject<Type> {
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

		@Override
		public boolean sameValueAs(final Type other) {
			return other != null && this.equals(other);
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
		notNull(track, "Track is required");
		notNull(eventTime, "Event time is required");
		notNull(type, "Release event type is required");
		notNull(store, "Store is required");

		if (type.requiresRelease()) {
			throw new IllegalArgumentException("Release is required for event type " + type);
		}

		this.eventTime = eventTime;
		this.type = type;
		this.store = store;
		this.track = track;
		this.release = null;
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
		notNull(track, "Track is required");
		notNull(eventTime, "Event time is required");
		notNull(type, "Release event type is required");
		notNull(store, "Store is required");
		notNull(release, "Release is required");

		this.release = release;
		this.eventTime = eventTime;
		this.type = type;
		this.store = store;
		this.track = track;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		return sameEventAs((ReleaseEvent) o);
	}

	@Override
	public boolean sameEventAs(final ReleaseEvent other) {
		return other != null && new EqualsBuilder()
				.append(this.track, other.track)
				.append(this.release, other.release)
				.append(this.eventTime, other.eventTime)
				.append(this.store, other.store)
				.append(this.type, other.type)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(track)
				.append(release)
				.append(eventTime)
				.append(store)
				.append(type)
				.hashCode();
	}

	ReleaseEvent() {
		// Needed by Hibernate
	}

	// Auto-generated surrogate key
	private Long id;

}
