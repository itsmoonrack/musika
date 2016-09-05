package io.musika.notifier.domain.model.shared.kernel;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * A record label.
 */
public class Label implements ValueObject<Label> {

    public static final Label NONE = new Label();

    @Override
    public boolean sameValueAs(Label other) {
        return false;
    }

    Label() {
        // Needed by Hibernate
    }

}
