package io.musika.notifier.domain.model.notifier;

import java.util.List;

import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.shared.kernel.Track;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Release extends io.musika.notifier.domain.model.shared.kernel.Release {

	/**
	 * Test if the given release event is expected when executing this release.
	 *
	 * @param event Event to test
	 * @return <code>true</code> if the event is expected
	 */
	public boolean isExpected(final ReleaseEvent event) {
		final List<Track> tracks = tracks();

		if (tracks.isEmpty()) {
			return true;
		}

		return tracks.contains(event.track());
	}

}
