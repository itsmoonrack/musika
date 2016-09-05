package io.musika.notifier.domain.model.release;

import io.musika.notifier.domain.model.shared.ValueObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * The release history of a track.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class ReleaseHistory implements ValueObject<ReleaseHistory> {

    public static final ReleaseHistory EMPTY = new ReleaseHistory(Collections.emptyList());

    private final List<ReleaseEvent> releaseEvents;

    public ReleaseHistory(Collection<ReleaseEvent> releaseEvents) {
        notNull(releaseEvents, "Release events are null");

        this.releaseEvents = new ArrayList<>(releaseEvents);
    }

	public ReleaseEvent mostRecentlyReleasedEvent() {
        // TODO
		return null;
	}

    @Override
    public boolean sameValueAs(ReleaseHistory other) {
        return other != null && this.releaseEvents.equals(other.releaseEvents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ReleaseHistory other = (ReleaseHistory) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return releaseEvents.hashCode();
    }

}
