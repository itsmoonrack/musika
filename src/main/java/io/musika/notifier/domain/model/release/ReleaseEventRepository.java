package io.musika.notifier.domain.model.release;

import io.musika.notifier.domain.model.shared.kernel.TrackId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Release event repository.
 */
public interface ReleaseEventRepository extends JpaRepository<ReleaseEvent, Long> {

	/**
	 * @param trackId track id
	 * @return The event history of this track
	 */
	ReleaseHistory lookupReleaseHistoryOfTrack(TrackId trackId);

}
