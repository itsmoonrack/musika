package io.musika.notifier.domain.model.shared.kernel;

import io.musika.notifier.domain.model.shared.Entity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Release implements Entity<Release, ReleaseNumber> {

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

		return sameIdentityAs(other);
	}

	@Override
	public boolean sameIdentityAs(final Release other) {
		return other != null && releaseNumber.equals(other.releaseNumber);
	}

	@Override
	public int hashCode() {
		return releaseNumber.hashCode();
	}

	@Override
	public ReleaseNumber identity() {
		return releaseNumber;
	}

}
