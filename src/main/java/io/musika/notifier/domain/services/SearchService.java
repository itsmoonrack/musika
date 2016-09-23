package io.musika.notifier.domain.services;

import java.util.List;

import io.musika.notifier.domain.model.shared.kernel.Artist;
import io.musika.notifier.domain.model.shared.kernel.TrackId;

/**
 * TrackId service.
 */
public interface SearchService {

    /**
     * @param trackName track's name
     * @return a list of track ids that satisfy the name. May be an empty list if no track id is found.
     */
    List<TrackId> getTrackIdSuggestions(String trackName);

    /**
     * @param artistName artist's name
     * @return a list of artists that satisfy the name. May be an empty list if no artists are found.
     */
    List<Artist> getArtistSuggestions(String artistName);

}
