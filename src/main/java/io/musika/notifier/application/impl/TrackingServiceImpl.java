package io.musika.notifier.application.impl;

import java.util.List;
import java.util.Set;

import io.musika.notifier.application.TrackingService;
import io.musika.notifier.domain.model.notifier.Catalog;
import io.musika.notifier.domain.model.notifier.SubscriptionRepository;
import io.musika.notifier.domain.model.shared.kernel.Artist;
import io.musika.notifier.domain.model.shared.kernel.Track;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.store.StoreRepository;
import io.musika.notifier.domain.services.CatalogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackingServiceImpl implements TrackingService {

	private final StoreRepository storeRepository;
	private final SubscriptionRepository subscriptionRepository;
	private final CatalogService catalogService;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public TrackingServiceImpl(final StoreRepository storeRepository,
							   final SubscriptionRepository subscriptionRepository,
							   final CatalogService catalogService) {
		this.storeRepository = storeRepository;
		this.subscriptionRepository = subscriptionRepository;
		this.catalogService = catalogService;
	}

	@Override
	public TrackId subscribeNewTrack(final Set<Artist> artists,
									 final String title,
									 final Set<Artist> remixers,
									 final String remixName) {
		return null;
	}

	@Override
	public List<Catalog> requestPossibleRecordsForTrack(TrackId trackId) {
		return null;
	}

	@Override
	public void attachTrackToCatalog(Catalog catalog, TrackId trackId) {

	}

	@Override
	public void changeTrack(TrackId trackId, Track track) {

	}
}
