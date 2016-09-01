package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * Sending status.
 */
public enum SendingStatus implements ValueObject<SendingStatus> {
	NOT_SENT, SENT, MISSENT;

	@Override
	public boolean sameValueAs(final SendingStatus other) {
		return this.equals(other);
	}

}
