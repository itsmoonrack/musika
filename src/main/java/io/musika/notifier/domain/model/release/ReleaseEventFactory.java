package io.musika.notifier.domain.model.release;

import java.net.URI;
import java.util.Date;

import io.musika.notifier.domain.model.shared.kernel.Release;
import io.musika.notifier.domain.model.shared.kernel.ReleaseNumber;
import io.musika.notifier.domain.model.shared.kernel.ReleaseRepository;
import io.musika.notifier.domain.model.notifier.Track;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.store.Store;
import io.musika.notifier.domain.model.store.StoreRepository;
import io.musika.notifier.domain.model.shared.kernel.TrackRepository;

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
        this.releaseRepository = releaseRepository;
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

	private Release findRelease(final ReleaseNumber releaseNumber) throws UnknownReleaseException {
		if (releaseNumber == null) {
			return null;
		}

		final Release release = releaseRepository.findOne(releaseNumber);

		if (releaseNumber == null) {
			throw new UnknownReleaseException(releaseNumber);
		}

		return release;
	}

    private Track findTrack(final TrackId trackId) throws UnknownTrackException {
        final Track track = trackRepository.findOne(trackId);

        if (track == null) {
			throw new UnknownTrackException(trackId);
		}

        return track;
    }

    private Store findStore(final URI uriCode) throws UnknownStoreException {
        final Store store = storeRepository.findOne(uriCode);

        if (store == null) {
            throw new UnknownStoreException(uriCode);
        }

        return store;
    }

}
