package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.User;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * The subscriber list.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Followers implements ValueObject<Followers> {

	private List<User> followers = Collections.emptyList();

	static final Followers EMPTY_FOLLOWERS = new Followers();

	public Followers(final List<User> followers) {
		notNull(followers);
		noNullElements(followers);

		this.followers = followers;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Followers other = (Followers) o;

		return sameValueAs(other);
	}

	@Override
	public boolean sameValueAs(final Followers other) {
		return other != null && followers.equals(other.followers);
	}

	@Override
	public int hashCode() {
		return followers.hashCode();
	}

	Followers() {
		// Needed by hibernate
	}

	// Auto-generated surrogate key
	private Long id;

}
