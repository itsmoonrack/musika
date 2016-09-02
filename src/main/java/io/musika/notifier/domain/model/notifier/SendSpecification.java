package io.musika.notifier.domain.model.notifier;

import static org.apache.commons.lang3.Validate.notNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.musika.notifier.domain.model.shared.Specification;
import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.Release;
import io.musika.notifier.domain.model.shared.kernel.Track;

/**
 * Track specification.
 *
 * Describes when a subscription should send notification to client.
 */
public class SendSpecification implements Specification<Release>, ValueObject<SendSpecification> {

	private Track track;

	/**
	 * @param track the track specification
	 */
	public SendSpecification(final Track track) {
		notNull(track, "Track is null");

		this.track = track;
	}

	@Override
	public boolean isSatisfiedBy(final Release release) {
		return release != null && release.containsTrack(track);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final SendSpecification other = (SendSpecification) o;

		return sameValueAs(other);
	}

	@Override
	public boolean sameValueAs(final SendSpecification other) {
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
