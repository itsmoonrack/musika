package io.musika.notifier.application;

import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.shared.kernel.UserId;

/**
 * Tracking notification service.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public interface TrackingService {

    /**
     * Registers a new track in the notification system, not yet subscribed.
     *
     * @param name	track name
     * @return Track id
     */
    TrackId createNewTrack(String name);

    /**
     * Requests the number of subscribers for a track id.
     *
     * @param trackId track id
     * @return The number of subscribers for this track
     */
    int requestSubscribersCountForRelease(TrackId trackId);

	/**
     * @param userId
     * @param trackId
     */
    void subscribeUserToRelease(UserId userId, TrackId trackId);

    /**
     * @param userId
     * @param trackId
     */
    void unsubscribeUserFromRelease(UserId userId, TrackId trackId);

}
