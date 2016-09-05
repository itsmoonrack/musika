package io.musika.notifier.application.impl;

import io.musika.notifier.application.ApplicationEvents;
import io.musika.notifier.application.SubscriptionInquiryService;
import io.musika.notifier.domain.model.notifier.Subscription;
import io.musika.notifier.domain.model.notifier.SubscriptionRepository;
import io.musika.notifier.domain.model.release.ReleaseEventRepository;
import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.Validate.notNull;

public class SubscriptionInquiryServiceImpl implements SubscriptionInquiryService {

    private final ApplicationEvents applicationEvents;
    private final SubscriptionRepository subscriptionRepository;
    private final ReleaseEventRepository releaseEventRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public SubscriptionInquiryServiceImpl(final ApplicationEvents applicationEvents,
                                          final SubscriptionRepository subscriptionRepository,
                                          final ReleaseEventRepository releaseEventRepository) {
        this.applicationEvents = applicationEvents;
        this.subscriptionRepository = subscriptionRepository;
        this.releaseEventRepository = releaseEventRepository;
    }

    @Override
    @Transactional
    public void querySubscription(final TrackId trackId) {
        notNull(trackId, "Track ID is null");

        final Subscription subscription = subscriptionRepository.findOne(trackId);
        if (subscription == null) {
            logger.warn("Unable to query non-existing subscription {}.", trackId);
            return;
        }

        final ReleaseHistory releaseHistory = releaseEventRepository.lookupReleaseHistoryOfTrack(trackId);

        subscription.deriveNotificationStatus(releaseHistory);

        if (subscription.notification().)
    }

}
