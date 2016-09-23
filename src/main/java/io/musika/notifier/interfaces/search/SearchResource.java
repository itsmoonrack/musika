package io.musika.notifier.interfaces.search;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.musika.notifier.domain.model.shared.kernel.Artist;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.services.SearchService;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
@Path("/search")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class SearchResource {

	private final SearchService searchService;

	public SearchResource(final SearchService searchService) {
		this.searchService = searchService;
	}

	@Path("/tracks/{trackName}")
	public List<TrackId> getTrackIdSuggestions(@PathParam("trackName") String trackName) {
		return searchService.getTrackIdSuggestions(trackName);
	}

	public List<Artist> getArtistSuggestions(String artistName) {
		return null;
	}

}
