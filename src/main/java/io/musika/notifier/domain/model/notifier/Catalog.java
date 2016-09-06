package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.shared.ValueObject;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * A list of possibles records for a particular track.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Catalog implements ValueObject<Catalog> {

	private List<Record> records = Collections.emptyList();

	static final Catalog EMPTY_CATALOG = new Catalog();

	public Catalog(final List<Record> records) {
		notNull(records);
		noNullElements(records);

		this.records = records;
	}

	/**
	 * @return the records, as an <b>immutable</b> list
	 */
	public List<Record> records() {
		return Collections.unmodifiableList(records);
	}

	/**
	 * Test if the given release event is expected when searching this catalog.
	 *
	 * @param event Event to test
	 * @return <code>true</code> if the event is expected
	 */
	public boolean isExpected(final ReleaseEvent event) {
		if (records.isEmpty()) {
			return true;
		}

		if (event.type() == ReleaseEvent.Type.RELEASED
                || event.type() == ReleaseEvent.Type.REMOVED) {
			for (Record record : records) {
				if (record.store().sameIdentityAs(event.store())
						&& record.release().sameValueAs(event.release())) {
				    return true;
				}
			}
			return false;
		}

		if (event.type() == ReleaseEvent.Type.SUBSCRIBED) {

        }

		return true;
	}

	/**
	 * @param other releases to compare
	 * @return <code>true</code> if the releases in this and other releases are all equal
	 */
	@Override
	public boolean sameValueAs(Catalog other) {
		return other != null && records.equals(other.records);
	}

	protected Catalog() {
		// Needed by Hibernate
	}

	// Auto-generated surrogate key
	private Long id;

}
