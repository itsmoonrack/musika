package io.musika.notifier.domain.model.shared.kernel;

import java.util.Collections;
import java.util.List;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class Release {

	private List<Track> tracks = Collections.emptyList();

	/**
	 * @return the tracks of this release, as an <b>immutable</b> list.
	 */
	public List<Track> tracks() {
		return Collections.unmodifiableList(tracks);
	}

}
