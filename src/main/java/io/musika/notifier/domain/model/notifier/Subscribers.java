package io.musika.notifier.domain.model.notifier;

import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.Collections;
import java.util.List;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * The subscriber list.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Subscribers implements ValueObject<Subscribers> {

	private List<User> subscribers = Collections.emptyList();

	static final Subscribers EMPTY_SUBSCRIBERS = new Subscribers();

	public Subscribers(final List<User> subscribers) {
		notNull(subscribers);
		noNullElements(subscribers);

		this.subscribers = subscribers;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Subscribers other = (Subscribers) o;

		return sameValueAs(other);
	}

	@Override
	public boolean sameValueAs(Subscribers other) {
		return other != null && subscribers.equals(other.subscribers);
	}

	@Override
	public int hashCode() {
		return subscribers.hashCode();
	}

	Subscribers() {
		// Needed by hibernate
	}

	// Auto-generated surrogate key
	private Long id;

}
