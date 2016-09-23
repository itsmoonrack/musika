package io.musika.notifier.interfaces.tracking;

import io.musika.notifier.domain.model.notifier.Notification;
import io.musika.notifier.domain.model.notifier.Subscription;
import io.musika.notifier.domain.model.release.ReleaseEvent;
import org.springframework.context.MessageSource;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * View adapter for displaying a subscription in a tracking context.
 */
public final class SubscriptionTrackingViewAdapter {

    private final Subscription subscription;
    private final MessageSource messageSource;
    private final Locale locale;
    private final List<ReleaseEventViewAdapter> events;
    private final String FORMAT = "yyyy-MM-dd hh:mm";
    private final TimeZone timeZone;

    public SubscriptionTrackingViewAdapter(final Subscription subscription,
                                           final MessageSource messageSource,
                                           final Locale locale,
                                           final List<ReleaseEvent> releaseEvents) {
        this(subscription, messageSource, locale, releaseEvents, TimeZone.getDefault());
    }

    public SubscriptionTrackingViewAdapter(final Subscription subscription,
                                           final MessageSource messageSource,
                                           final Locale locale,
                                           final List<ReleaseEvent> releaseEvents,
                                           final TimeZone timeZone) {
        this.messageSource = messageSource;
        this.locale = locale;
        this.subscription = subscription;
        this.timeZone = timeZone;

        this.events = releaseEvents.stream()
                .map(e -> new ReleaseEventViewAdapter(e))
                .collect(Collectors.toList());
    }

    /**
     * @return A translated string describing the subscription status.
     */
    public String getStatusText() {
        final Notification notification = subscription.notification();
        final String code = "subscription.status." + notification.availability().name();
        final Object[] args = null;

        return messageSource.getMessage(code, args, locale);
    }

    /**
     * @return Subscription track id.
     */
    public String getTrackId() {
        return subscription.identity().idString();
    }

    /**
     * @return Subscription followers count.
     */
    public String getFollowersCount() {
        final String code = "subscription.followers";
        final Object[] args = new Object[] {subscription.followers()};

        return messageSource.getMessage(code, args, locale);
    }

    /**
     * @return An unmodifiable list of release event view adapters.
     */
    public List<ReleaseEventViewAdapter> getEvents() {
        return Collections.unmodifiableList(events);
    }

    /**
     * Release event view adapter component.
     */
    public final class ReleaseEventViewAdapter {

        private final ReleaseEvent releaseEvent;

        /**
         * Constructor.
         *
         * @param releaseEvent release event
         */
        public ReleaseEventViewAdapter(final ReleaseEvent releaseEvent) {
            this.releaseEvent = releaseEvent;
        }

        /**
         * @return Name of the store where the event occured.
         */
        public String getStoreName() {
            return releaseEvent.store().name();
        }

        /**
         * @return Type of event.
         */
        public String getType() {
            return releaseEvent.type().toString();
        }

        public String getDescription() {
            final String code = "releaseHistory.eventDescription." + releaseEvent.type().name();
            final Object[] args;

            switch (releaseEvent.type()) {
                case RELEASED:
                    args = new Object[] {
                        releaseEvent.store().name(),
                        releaseEvent.releaseDate()
                    };
                    break;
                default:
                    args = new Object[] {};
            }

            return messageSource.getMessage(code, args, locale);
        }

    }

}
