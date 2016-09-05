package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.shared.Specification;
import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.Track;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Track specification.
 *
 * Describes when a subscription should send notification to client.
 */
public class TrackSpecification implements Specification<Catalog>, ValueObject<TrackSpecification> {

	private Track track;

	/**
	 * @param track the track specification
	 */
	public TrackSpecification(final Track track) {
		notNull(track, "Track is null");

		this.track = track;
	}

	@Override
	public boolean isSatisfiedBy(final Catalog catalog) {
		return catalog != null;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final TrackSpecification other = (TrackSpecification) o;

		return sameValueAs(other);
	}

	@Override
	public boolean sameValueAs(final TrackSpecification other) {
		return other != null && new EqualsBuilder()
				.append(this.track, other.track)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.track)
				.hashCode();
	}

}
