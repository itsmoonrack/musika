package io.musika.notifier.application.impl;

import java.net.URI;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import io.musika.notifier.application.ApplicationEvents;
import io.musika.notifier.application.ReleaseEventService;
import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.release.ReleaseEventFactory;
import io.musika.notifier.domain.model.release.ReleaseEventRepository;
import io.musika.notifier.domain.model.release.ReleaseNumber;
import io.musika.notifier.domain.model.release.UnableToCreateReleaseEventException;
import io.musika.notifier.domain.model.track.TrackId;

public final class ReleaseEventServiceImpl implements ReleaseEventService {

    private final ApplicationEvents applicationEvents;
    private final ReleaseEventRepository releaseEventRepository;
    private final ReleaseEventFactory releaseEventFactory;
    private final Logger logger = LoggerFactory.getLogger(ReleaseEventServiceImpl.class);

    public ReleaseEventServiceImpl(final ReleaseEventRepository releaseEventRepository,
								   final ReleaseEventFactory releaseEventFactory,
                                   final ApplicationEvents applicationEvents) {
		this.releaseEventRepository = releaseEventRepository;
		this.applicationEvents = applicationEvents;
		this.releaseEventFactory = releaseEventFactory;
	}

	@Transactional(rollbackFor = UnableToCreateReleaseEventException.class)
    public void registerReleaseEvent(final Date eventTime,
									 final TrackId trackId,
									 final ReleaseNumber releaseNumber,
									 final URI uriCode,
									 final ReleaseEvent.Type type) throws UnableToCreateReleaseEventException {
		// Using a factory to create a ReleaseEvent (aggregate). This is where
		// it is determined whether the incoming data, the attempt, actually is
		// capable of representing a real release event.
		final ReleaseEvent event = releaseEventFactory.createReleaseEvent(eventTime, trackId, releaseNumber, uriCode, type);

		// TODO: Check this comment
		// Stores the new release event, which updates the persistent state
		// of the release event aggregate (but not the track aggregate,
		// that happens asynchronously!).
		releaseEventRepository.save(event);

		// Publishes an event stating that a track has been notified.
		applicationEvents.trackWasNotified(event);

		logger.info("Registered release event");
    }

}
