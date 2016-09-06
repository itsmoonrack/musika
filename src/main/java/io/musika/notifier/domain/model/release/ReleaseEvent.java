package io.musika.notifier.domain.model.release;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.musika.notifier.domain.model.notifier.Subscription;
import io.musika.notifier.domain.model.shared.DomainEvent;
import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.Release;
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
    private Release release;
    private Store store;
    private Date eventTime;
    private Date releaseDate;
    private Subscription subscription;

    /**
     * Release event type.
     */
    public enum Type implements ValueObject<Type> {
        RELEASED(true),
        EXCLUSIVE(true),
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
     * @param subscription		subscription
     * @param eventTime			event time
     * @param type              type of event
     * @param store  			where the event took place
     */
    public ReleaseEvent(final Subscription subscription,
                        final Date eventTime,
                        final Type type,
                        final Store store) {
        notNull(subscription, "Subscription is null");
        notNull(eventTime, "Event time is null");
        notNull(type, "Release event type is null");
        notNull(store, "Store is null");

        if (type.requiresRelease()) {
            throw new IllegalArgumentException("Release is required for event type " + type);
        }

        this.type = type;
        this.store = store;
        this.eventTime = eventTime;
        this.subscription = subscription;
        this.release = null;
    }

    /**
     * @param subscription		subscription
     * @param eventTime			event time
     * @param type              type of event
     * @param store  			where the event took place
     * @param release			the release
     * @param releaseDate       the release date
     */
    public ReleaseEvent(final Subscription subscription,
                        final Date eventTime,
                        final Type type,
                        final Store store,
                        final Release release,
                        final Date releaseDate) {
        notNull(subscription, "Subscription is null");
        notNull(eventTime, "Event time is null");
        notNull(type, "Release event type is null");
        notNull(store, "Store is null");
        notNull(release, "Release is null");
        notNull(releaseDate, "Release date is null");

        this.type = type;
        this.store = store;
        this.release = release;
        this.eventTime = eventTime;
        this.releaseDate = releaseDate;
        this.subscription = subscription;
    }

    public Type type() {
        return this.type;
    }

    public Release release() {
        return defaultIfNull(this.release, Release.NONE);
    }

    public Date eventTime() {
        return this.eventTime;
    }

    public Date releaseDate() {
        return this.releaseDate;
    }

    public Store store() {
        return this.store;
    }

    public Subscription subscription() {
        return this.subscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReleaseEvent other = (ReleaseEvent) o;

        return sameEventAs(other);
    }

    @Override
    public boolean sameEventAs(final ReleaseEvent other) {
        return other != null && new EqualsBuilder()
                .append(this.subscription, other.subscription)
                .append(this.release, other.release)
                .append(this.eventTime, other.eventTime)
                .append(this.store, other.store)
                .append(this.type, other.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(subscription)
                .append(release)
                .append(eventTime)
                .append(store)
                .append(type)
                .hashCode();
    }

    protected ReleaseEvent() {
        // Needed by Hibernate
    }

    // Auto-generated surrogate key
    private Long id;

}
