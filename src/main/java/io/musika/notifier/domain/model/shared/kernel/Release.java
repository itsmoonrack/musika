package io.musika.notifier.domain.model.shared.kernel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.musika.notifier.application.util.DateTestUtil;
import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Release implements ValueObject<Release> {

	private Date releaseDate;
	private RecordLabel recordRecordLabel;
	private String releaseName;
	private ReleaseNumber releaseNumber;
	private List<Track> tracks;

	// Null object pattern TODO see Optional ?
    public static final Release NONE = new Release(
            new ReleaseNumber(""), "None", RecordLabel.NONE, new Date(), Collections.emptyList()
    );

    public Release(final ReleaseNumber releaseNumber,
				   final String releaseName,
				   final RecordLabel recordRecordLabel,
				   final Date releaseDate,
				   final List<Track> tracks) {
        Validate.notNull(releaseNumber, "Release number is null");
        Validate.notNull(releaseName, "Release name is null");
        Validate.notBlank(releaseName, "Release name is blank");
		Validate.notNull(recordRecordLabel, "RecordLabel is null");

        this.releaseDate = releaseDate;
		this.releaseName = releaseName;
        this.releaseNumber = releaseNumber;
        this.recordRecordLabel = recordRecordLabel;
		this.tracks = tracks;
    }

	/**
	 * @return Release number.
	 */
	public ReleaseNumber releaseNumber() {
		return releaseNumber;
	}

	/**
	 * @return Tracks of this release, as an <b>immutable</b> list.
	 */
	public List<Track> tracks() {
		return Collections.unmodifiableList(tracks);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Release other = (Release) o;

		return sameValueAs(other);
	}

	@Override
	public boolean sameValueAs(final Release other) {
		return other != null && new EqualsBuilder()
				.append(this.recordRecordLabel, other.recordRecordLabel)
				.append(this.releaseDate, other.releaseDate)
				.append(this.releaseNumber, other.releaseNumber)
				.append(this.tracks, other.tracks)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(recordRecordLabel)
				.append(releaseDate)
				.append(releaseNumber)
				.append(tracks)
				.hashCode();
	}

	protected Release() {
		// Needed by Hibernate
	}

	// Auto-generated surrogate key
	private Long id;

	/**
	 * Builder pattern is used for incremental construction
	 * of a Release value object.
	 */
	public static final class Builder {

		private final List<Track> tracks = new ArrayList<>();
		private final ReleaseNumber releaseNumber;
		private final String releaseName;
		private final RecordLabel recordLabel;
		private Date releaseDate;

		public Builder(final ReleaseNumber releaseNumber, final String releaseName, final RecordLabel recordLabel) {
			Validate.notNull(releaseNumber, "Release number is null");
			Validate.notNull(recordLabel, "Record label is null");

			this.releaseName = releaseName;
			this.releaseNumber = releaseNumber;
			this.recordLabel = recordLabel;
		}

		public Builder addTrack(final Track track) {
			tracks.add(track);
			return this;
		}

		public Builder setReleaseDate(final String releaseDate) {
			this.releaseDate = DateTestUtil.toDate(releaseDate);
			return this;
		}

		public Release build() {
			return new Release(releaseNumber, releaseName, recordLabel, releaseDate, tracks);
		}

	}

}
