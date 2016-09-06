package io.musika.notifier.domain.model.shared.kernel;

import io.musika.notifier.domain.service.ArtistService;

/**
 * Creates tracks.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class TrackFactory {

	private final ArtistService artistService;

	public TrackFactory(final ArtistService artistService) {
		this.artistService = artistService;
	}



}
