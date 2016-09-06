package io.musika.notifier.presentation.tracking;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link TrackCommand}s.
 */
public final class TrackCommandValidator implements Validator {

    @Override
    public boolean supports(final Class<?> aClass) {
        return TrackCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "trackId", "error.required", "Required");
    }
}
