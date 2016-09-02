package io.musika.notifier.domain.model.notifier;

import static org.apache.commons.lang3.Validate.notNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.musika.notifier.domain.model.shared.ValueObject;
import io.musika.notifier.domain.model.shared.kernel.Release;
import io.musika.notifier.domain.model.store.Store;

/**
 * A catalog consists of one or more (physical) record.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Record implements ValueObject<Record> {

	private Store store;
	private Release release;

	public Record(final Store store, final Release release) {
		notNull(store, "Store is null");
		notNull(release, "Release is null");

		this.store = store;
		this.release = release;
	}

	public Store store() {
		return store;
	}

	public Release release() {
		return release;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Record other = (Record) o;

		return sameValueAs(other);
	}

	@Override
	public boolean sameValueAs(Record other) {
		return other != null && new EqualsBuilder()
				.append(this.store, other.store)
				.append(this.release, other.release)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(store)
				.append(release)
				.toHashCode();
	}

	Record() {
		// Needed by Hibernate
	}

	// Auto-generated surrogate key
	private Long id;

}
