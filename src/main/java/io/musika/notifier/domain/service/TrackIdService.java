package io.musika.notifier.domain.service;

import io.musika.notifier.domain.model.shared.kernel.TrackId;

import java.util.List;

/**
 * TrackId service.
 */
public interface TrackIdService {

    /**
     * @param trackName track's name
     * @return a list of track ids that satisfy the name. May be an empty list if no track id is found.
     */
    List<TrackId> fetchTrackIdsForName(String trackName);

}
