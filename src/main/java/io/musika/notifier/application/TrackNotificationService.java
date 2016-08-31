package io.musika.notifier.application;

import io.musika.notifier.domain.model.release.Release;
import io.musika.notifier.domain.model.track.TrackId;

import java.util.List;

/**
 * Track notification service.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public interface TrackNotificationService {

    /**
     * Registers a new track in the notification system, not yet subscribed.
     *
     * @param name	track name
     * @return Track id
     */
    TrackId createNewTrack(String name);

    /**
     * Requests a list of releases for this track.
     *
     * @param trackId track id
     * @return A list of possible releases for this track
     */
    List<Release> requestReleasesForTrack(TrackId trackId);

    void subscribeNotificationForUser(TrackId trackId, UserId userId);

}
