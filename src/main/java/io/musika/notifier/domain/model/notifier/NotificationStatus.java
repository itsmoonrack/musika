package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * Sending status.
 */
public enum NotificationStatus implements ValueObject<NotificationStatus> {
	NOT_SENT, RELEASED, CHANGED;

	@Override
	public boolean sameValueAs(final NotificationStatus other) {
		return this.equals(other);
	}

}
