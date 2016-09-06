package io.musika.notifier.domain.model.shared.kernel;

import io.musika.notifier.domain.model.shared.Entity;

import java.time.Year;
import java.util.Set;

/**
 * A track.
 */
public class Track implements Entity<Track, TrackId> {

	private TrackId trackId;
	private Set<Artist> artists;
	private String title;
    private Set<Artist> remixers;
	private Year year;

	@Override
	public boolean sameIdentityAs(Track other) {
		return other != null && this.trackId.equals(other.trackId);
	}

	@Override
	public TrackId identity() {
		return trackId;
	}

	protected Track() {
		// Needed by Hibernate
	}

    // Auto-generated surrogate key
	private Long id;

}
