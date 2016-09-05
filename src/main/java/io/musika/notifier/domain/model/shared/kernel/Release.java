package io.musika.notifier.domain.model.shared.kernel;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.musika.notifier.domain.model.shared.Entity;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Release implements Entity<Release, ReleaseNumber> {

	private Date releaseDate;
	private Label recordLabel;
	private ReleaseNumber releaseNumber;
	private List<Track> tracks = Collections.emptyList();

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
