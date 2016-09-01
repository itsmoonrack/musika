package io.musika.notifier.application.impl;

import io.musika.notifier.application.TrackingService;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.shared.kernel.TrackRepository;
import io.musika.notifier.domain.model.shared.kernel.UserId;
import io.musika.notifier.domain.model.store.StoreRepository;

public class TrackingServiceImpl implements TrackingService {

	private final TrackRepository trackRepository;
	private final StoreRepository storeRepository;

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
