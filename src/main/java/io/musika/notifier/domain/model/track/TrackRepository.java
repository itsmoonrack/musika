package io.musika.notifier.domain.model.track;

import io.musika.notifier.domain.model.Track;
import io.musika.notifier.domain.model.TrackId;

public interface TrackRepository {

	/**
	 * Finds a track using given id.
	 *
	 * @param trackId Id
	 * @return Track if found, else {@code null}
	 */
	Track find(TrackId trackId);

}
