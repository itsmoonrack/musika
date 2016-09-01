package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.shared.Specification;
import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.Release;

/**
 * Track specification.
 *
 * Describes
 */
public class TrackSpecification implements Specification<Release>, ValueObject<TrackSpecification> {

	@Override
	public boolean isSatisfiedBy(Release o) {
		return false;
	}

	@Override
	public boolean sameValueAs(TrackSpecification other) {
		return false;
	}

}
