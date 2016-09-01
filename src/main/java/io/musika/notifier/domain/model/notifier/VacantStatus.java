package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * Represents the different availability statuses for a subscription.
 */
public enum VacantStatus implements ValueObject<VacantStatus> {
	NOT_AVAILABLE, AVAILABLE, UNKNOWN;

	@Override
	public boolean sameValueAs(final VacantStatus other) {
		return this.equals(other);
	}

}
