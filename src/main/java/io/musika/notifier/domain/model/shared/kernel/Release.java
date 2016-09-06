package io.musika.notifier.domain.model.shared.kernel;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Release implements ValueObject<Release> {

	private Date releaseDate;
	private Label recordLabel;
	private ReleaseNumber releaseNumber;
	private List<Track> tracks = Collections.emptyList();

	// Null object pattern TODO see Optional ?
    public static final Release NONE = new Release(
            new ReleaseNumber(""), Label.NONE, new Date()
    );

    public Release(final ReleaseNumber releaseNumber, final Label recordLabel, final Date releaseDate) {
        notNull(releaseNumber, "Release number is null");
        notNull(recordLabel, "Label is null");

        this.releaseDate = releaseDate;
        this.releaseNumber = releaseNumber;
        this.recordLabel = recordLabel;
    }

	/**
	 * @return the tracks of this release, as an <b>immutable</b> list.
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
				.append(this.recordLabel, other.recordLabel)
				.append(this.releaseDate, other.releaseDate)
				.append(this.releaseNumber, other.releaseNumber)
				.append(this.tracks, other.tracks)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(recordLabel)
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

}
