package io.musika.notifier.domain.model.shared.kernel;

import io.musika.notifier.domain.model.shared.ValueObject;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * An artist.
 */
public class Artist implements ValueObject<Artist> {

	private String name;
	private Integer order;
	private List<Artist> artists = Collections.EMPTY_LIST;

	public static final Artist EMPTY = new Artist("");

	Artist(final String name) {
		this(name, new ArrayList<>());
	}

	Artist(final String name, final List<Artist> artists) {
		this(name, artists, 0);
	}

	Artist(final String name, final List<Artist> artists, final Integer order) {
		this.name = name;
		this.artists = artists;
		this.order = order;
	}

	public List<Artist> artists() {
		return Collections.unmodifiableList(artists);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Artist other = (Artist) o;

		return sameValueAs(other);
	}

	@Override
	public boolean sameValueAs(final Artist other) {
		return other != null
				&& this.name.equals(other.name)
				&& this.artists.equals(other.artists);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(this.artists).toHashCode();
	}

	private static final Comparator<Artist> BY_ORDER_COMPARATOR =
			(a1, a2) -> a1.order.compareTo(a2.order);

	Artist() {
		// Needed by Hibernate
	}

}
