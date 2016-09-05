package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * Represents the different availability statuses for a subscription.
 */
public enum Availability implements ValueObject<Availability> {
	NOT_AVAILABLE, AVAILABLE, UNKNOWN;

	@Override
	public boolean sameValueAs(final Availability other) {
		return this.equals(other);
	}

}
