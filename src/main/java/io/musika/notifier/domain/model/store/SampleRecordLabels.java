package io.musika.notifier.domain.model.store;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.musika.notifier.domain.model.shared.kernel.RecordLabel;

/**
 * Sample record labels, for test purposes.
 */
public final class SampleRecordLabels {

	public static final RecordLabel OSCIL8_RECORDINGS =
			new RecordLabel("OSCIL8 Recordings");

	public static final List<RecordLabel> ALL = new ArrayList<>();

	static {
		for (Field field : SampleRecordLabels.class.getDeclaredFields()) {
			if (field.getType().equals(Store.class)) {
				try {
					ALL.add((RecordLabel) field.get(null));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static List<RecordLabel> getAll() {
		return new ArrayList<>(ALL);
	}

}
