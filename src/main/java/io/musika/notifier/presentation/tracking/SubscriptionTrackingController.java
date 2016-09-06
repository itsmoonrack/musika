package io.musika.notifier.presentation.tracking;

import io.musika.notifier.domain.model.notifier.Subscription;
import io.musika.notifier.domain.model.notifier.SubscriptionRepository;
import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.release.ReleaseEventRepository;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Controller for tracking subscription. This interface sits immediately on top of the
 * domain layer, unlike the
 */
@Controller
public final class SubscriptionTrackingController {

    private SubscriptionRepository subscriptionRepository;
    private ReleaseEventRepository releaseEventRepository;
    private MessageSource messageSource;


    @RequestMapping(method = RequestMethod.GET)
    public Map<String, SubscriptionTrackingViewAdapter> get(final TrackCommand trackCommand) {
        return new HashMap<>();
    }

    @RequestMapping(method = RequestMethod.POST)
    protected Map<String, SubscriptionTrackingViewAdapter> onSubmit(final HttpServletRequest request,
                                                                    final TrackCommand command,
                                                                    final BindingResult bindingResult) {
        new TrackCommandValidator().validate(command, bindingResult);

        final TrackId trackId = new TrackId(command.getTrackId());
        final Subscription subscription = subscriptionRepository.findOne(trackId);
        final Map<String, SubscriptionTrackingViewAdapter> model = new HashMap<>();

        if (subscription != null) {
            final Locale locale = RequestContextUtils.getLocale(request);
            final List<ReleaseEvent> releaseEvents = releaseEventRepository
                    .lookupReleaseHistoryOfTrack(trackId)
                    .distinctEventsByCompletionTime();
            model.put("subscription", new SubscriptionTrackingViewAdapter(subscription, messageSource, locale, releaseEvents));
        } else {
            bindingResult.rejectValue("trackId", "subscription.unknown_id", new Object[]{command.getTrackId()}, "Unknown track id");
        }

        return model;
    }

    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void setReleaseEventRepository(ReleaseEventRepository releaseEventRepository) {
        this.releaseEventRepository = releaseEventRepository;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
