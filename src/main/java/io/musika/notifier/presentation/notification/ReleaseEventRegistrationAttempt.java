package io.musika.notifier.presentation.notification;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

import io.musika.notifier.domain.model.shared.kernel.ReleaseNumber;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.release.ReleaseEvent;

/**
 * Simple transfer object for passing incoming notification event registration attempts
 * to proper registration procedure.
 *
 * It is used as a message queue element.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public final class ReleaseEventRegistrationAttempt implements Serializable {

    private final Date eventTime;
    private final TrackId trackId;
    private final ReleaseNumber releaseNumber;
    private final ReleaseEvent.Type type;
    private final URI uriCode;

    public ReleaseEventRegistrationAttempt(final Date eventTime,
                                           final TrackId trackId,
                                           final ReleaseNumber releaseNumber,
                                           final ReleaseEvent.Type type,
                                           final URI uriCode) {
        this.eventTime = eventTime;
        this.trackId = trackId;
        this.releaseNumber = releaseNumber;
        this.type = type;
        this.uriCode = uriCode;
    }

    public Date getEventTime() {
        return new Date(eventTime.getTime());
    }

    public TrackId getTrackId() {
        return trackId;
    }

    public ReleaseNumber getReleaseNumber() {
        return releaseNumber;
    }

    public ReleaseEvent.Type getType() {
        return type;
    }

    public URI getUriCode() {
        return uriCode;
    }
}
