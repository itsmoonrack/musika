package io.musika.notifier.application;

import io.musika.notifier.domain.model.notifier.Catalog;
import io.musika.notifier.domain.model.notifier.Record;
import io.musika.notifier.domain.model.shared.kernel.Artist;
import io.musika.notifier.domain.model.shared.kernel.Track;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.shared.kernel.UserId;

import java.util.List;
import java.util.Set;

/**
 * Tracking notification service.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public interface TrackingService {

    /**
     * Registers a new track in the notification system, not yet subscribed.
     *
     * @param artists   list of artists
     * @param title     track's title
     * @param remixers  list of remixers
     * @param remixName name of the remix
     * @return Track id
     */
    TrackId subscribeNewTrack(Set<Artist> artists, String title, Set<Artist> remixers, String remixName);

    /**
     * Requests a list of catalogs for a track id.
     *
     * @param trackId track id
     * @return A list of possible catalogs for this subscription
     */
    List<Catalog> requestPossibleRecordsForTrack(TrackId trackId);

	/**
     * @param catalog
     * @param trackId
     */
    void attachTrackToCatalog(Catalog catalog, TrackId trackId);

	/**
     * Changes the track of a subscription.
     *
     * @param trackId   subscription track id
     * @param track     new track to watch <-- TODO this is bad
     */
    void changeTrack(TrackId trackId, Track track);

}
