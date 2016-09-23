package io.musika.notifier.domain.model.shared.kernel;

import org.apache.commons.lang3.Validate;

import io.musika.notifier.domain.model.shared.ValueObject;

/**
 * A record label.
 */
public class RecordLabel implements ValueObject<RecordLabel> {

    private String name;

    public static final RecordLabel NONE = new RecordLabel();

    public RecordLabel(final String name) {
        Validate.notNull(name, "Name is null");
        Validate.notBlank(name, "Name is blank");

        this.name = name;
    }

    @Override
    public boolean sameValueAs(final RecordLabel other) {
        return false;
    }

    protected RecordLabel() {
        // Needed by Hibernate
    }

    // Auto-generated surrogate key
    private Long id;

}
