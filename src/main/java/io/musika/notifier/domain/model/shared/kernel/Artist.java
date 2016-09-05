package io.musika.notifier.domain.model.shared.kernel;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * An artist.
 */
public class Artist implements ValueObject<Artist> {

	private String name;

	public static final Artist EMPTY = new Artist("");

	Artist(final String name) {
		this.name = name;
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
		return other != null && this.name.equals(other.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	Artist() {
		// Needed by Hibernate
	}

}
