package io.musika.notifier.domain.service;

import io.musika.notifier.domain.model.shared.kernel.Artist;

import java.util.Set;

/**
 * Artist service.
 */
public interface ArtistService {

    /**
     * @param artistName artist's name
     * @return a list of artists that satisfy the name. May be an empty list if no artists are found.
     */
    Set<Artist> fetchArtistsForName(String artistName);

	/**
     * @param artistName artist's name
     * @return an artist matching the exact name. May be null if no artist is found.
     */
    Artist findArtistByName(String artistName);

}
