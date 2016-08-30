package io.musika.notifier.application;

import io.musika.notifier.domain.model.TrackId;

/**
 * Store status service.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public interface StoreStatusService {

	/**
	 * Query store and send relevant notifications to interested parties,
	 * for example if a track has been released, or removed from a particular store.
	 *
	 * @param trackId track id
	 */
	void queryStore(TrackId trackId);

}
