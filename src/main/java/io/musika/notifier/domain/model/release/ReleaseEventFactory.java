package io.musika.notifier.domain.model.release;

import java.net.URI;
import java.util.Date;

import io.musika.notifier.domain.model.track.Track;
import io.musika.notifier.domain.model.track.TrackId;
import io.musika.notifier.domain.model.store.Store;
import io.musika.notifier.domain.model.store.StoreRepository;
import io.musika.notifier.domain.model.track.TrackRepository;

/**
 * Creates release events.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class ReleaseEventFactory {

	private final TrackRepository trackRepository;
	private final StoreRepository storeRepository;
	private final ReleaseRepository releaseRepository;

	public ReleaseEventFactory(final TrackRepository trackRepository,
							   final StoreRepository storeRepository,
							   final ReleaseRepository releaseRepository) {
		this.trackRepository = trackRepository;
		this.storeRepository = storeRepository;
	}

	/**
	 * @param eventTime		time when this event was received by the system
	 * @param trackId       track id
	 * @param releaseNumber release number
	 * @param uriCode       Uniform Resource Identifier (URI) of the store
	 * @param type          type of event
	 * @return A release event
	 */
	public ReleaseEvent createReleaseEvent(Date eventTime, TrackId trackId, ReleaseNumber releaseNumber, URI uriCode, ReleaseEvent.Type type)
		throws UnableToCreateReleaseEventException {
		final Track track = findTrack(trackId);
		final Store store = findStore(uriCode);
		final Release release = findRelease(releaseNumber);

		try {
			if (release == null) {
				return new ReleaseEvent(track, eventTime, type, store);
			} else {
				return new ReleaseEvent(track, eventTime, type, store, release);
			}
		} catch (Exception e) {
			throw new UnableToCreateReleaseEventException(e);
		}
	}

	private Track findTrack(final TrackId trackId) throws UnknownTrackException {

	}

	private Store findStore(final URI uriCode) throws UnknownStoreException {
		final Store store = storeRepository.find(uriCode);

		if (store == null) {
			throw new UnknownStoreException(uriCode);
		}

		return store;
	}

}
