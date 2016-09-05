package io.musika.notifier.application.impl;

import io.musika.notifier.application.TrackingService;
import io.musika.notifier.domain.model.notifier.SubscriptionRepository;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.shared.kernel.TrackRepository;
import io.musika.notifier.domain.model.shared.kernel.UserId;
import io.musika.notifier.domain.model.store.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackingServiceImpl implements TrackingService {

	private final StoreRepository storeRepository;
	private final SubscriptionRepository subscriptionRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public TrackingServiceImpl(final StoreRepository storeRepository,
							   final SubscriptionRepository subscriptionRepository) {
		this.storeRepository = storeRepository;
		this.subscriptionRepository = subscriptionRepository;
	}

	@Override
	public TrackId createNewTrack(String name) {
		return null;
	}

	@Override
	public int requestSubscribersCountForRelease(TrackId trackId) {
		return 0;
	}

	@Override
	public void subscribeUserToRelease(UserId userId, TrackId trackId) {

	}

	@Override
	public void unsubscribeUserFromRelease(UserId userId, TrackId trackId) {

	}

}
