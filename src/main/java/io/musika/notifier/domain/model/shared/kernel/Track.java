package io.musika.notifier.domain.model.shared.kernel;

import io.musika.notifier.domain.model.shared.Entity;

/**
 * A track.
 */
public class Track implements Entity<Track, TrackId> {

	private TrackId trackId;
	private Artist artist;

	@Override
	public boolean sameIdentityAs(Track other) {
		return other != null && this.trackId.equals(other.trackId);
	}

	@Override
	public TrackId identity() {
		return trackId;
	}

}
