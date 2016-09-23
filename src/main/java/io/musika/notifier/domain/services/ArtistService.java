package io.musika.notifier.domain.services;

import io.musika.notifier.domain.model.shared.kernel.Artist;

/**
 * Artist service.
 */
public interface ArtistService {

	/**
     * @param artistName artist's name
     * @return an artist matching the exact name. May be null if no artist is found.
     */
    Artist findArtistByName(String artistName);

}
