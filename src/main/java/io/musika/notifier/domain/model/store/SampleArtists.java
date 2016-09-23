package io.musika.notifier.domain.model.store;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.musika.notifier.domain.model.shared.kernel.Artist;

/**
 * Sample artists, for test purposes.
 */
public final class SampleArtists {

	public static final Artist MOONRACK =
			new Artist("Moonrack");

	public static final Artist SETIAWAN =
			new Artist("Setiawan");

	public static final Artist GIBBS_MASTER =
			new Artist("Gibb's Master");

	public static final Artist EKLPX =
			new Artist("EKLPX");

	public static final Artist MERSEL =
			new Artist("Mersel");

	public static final Artist DJ_PANGOLIN =
			new Artist("DJ Pangolin");

	public static final Artist DUST_YARD =
			new Artist("Dust Yard");

	public static final Artist VOICE_OF_SYLENTH =
			new Artist("Voice Of Sylenth");

	public static final Artist PIERROTECHNIQUE =
			new Artist("pierrotechnique");

	public static final Artist ODEN =
			new Artist("Oden");

	public static final List<Artist> ALL = new ArrayList<>();

	static {
		for (Field field : SampleArtists.class.getDeclaredFields()) {
			if (field.getType().equals(Store.class)) {
				try {
					ALL.add((Artist) field.get(null));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static List<Artist> getAll() {
		return new ArrayList<>(ALL);
	}

}
