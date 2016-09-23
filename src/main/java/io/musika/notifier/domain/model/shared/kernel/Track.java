package io.musika.notifier.domain.model.shared.kernel;

import io.musika.notifier.domain.model.shared.Entity;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.Validate;

/**
 * A track.
 */
public class Track implements Entity<Track, TrackId> {

	private TrackId trackId;
	private Set<Artist> artists;
	private String title;
    private Set<Artist> remixers;
	private Year year;

	public Track(final Set<Artist> artists,
				 final String title) {
		Validate.notNull(artists, "Artists is null");
		Validate.noNullElements(artists, "Artists is empty");
		Validate.notNull(title, "Title is null");
		Validate.notBlank(title, "Title is blank");

		this.artists = artists;
		this.title = title;
		//		this.trackId = generate track id
	}

	public Track(final Set<Artist> artists,
				 final String title,
				 final Set<Artist> remixers) {
		Validate.notNull(artists, "Artists is null");
		Validate.noNullElements(artists, "Artists is empty");
		Validate.notNull(title, "Title is null");
		Validate.notBlank(title, "Title is blank");

		this.artists = artists;
		this.title = title;
		this.remixers = remixers;
//		this.trackId = generate track id
	}

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

	/**
	 * Builder pattern is used for incremental construction
	 * of a Track aggregate. This serves as an aggregate factory.
	 */
	public static final class Builder {

		private final Set<Artist> artists = new HashSet<>();
		private final Set<Artist> remixers = new HashSet<>();
		private final String title;

		public Builder(final Artist artist, final String title) {
			Validate.notNull(artist, "Artist is null");
			Validate.notNull(title, "Title is null");

			this.artists.add(artist);
			this.title = title;
		}

		public Builder addArtist(final Artist artist) {
			Validate.notNull(artist, "Artist is null");

			this.artists.add(artist);
			return this;
		}

		public Builder addRemixer(final Artist remixer) {
			Validate.notNull(remixer, "Remixer is null");

			this.remixers.add(remixer);
			return this;
		}

		public Builder setMixName(final String mixName) {
			// TODO !
			return this;
		}

		public Track build() {
			return new Track(artists, title, remixers);
		}

	}

}
