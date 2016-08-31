package io.musika.notifier.domain.model.release;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The release history of a track.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class ReleaseHistory {

	public static final ReleaseHistory EMPTY = new ReleaseHistory(Collections.emptyList());

	private final List<ReleaseEvent> releaseEvents;

	public ReleaseHistory(Collection<ReleaseEvent> releaseEvents) {
		notNull(releaseEvents, "Release events are required");

		this.releaseEvents = new ArrayList<>(releaseEvents);
	}

}
