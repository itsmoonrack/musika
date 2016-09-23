package io.musika.notifier.domain.model.store;

import static io.musika.notifier.domain.model.store.SampleArtists.DJ_PANGOLIN;
import static io.musika.notifier.domain.model.store.SampleArtists.DUST_YARD;
import static io.musika.notifier.domain.model.store.SampleArtists.EKLPX;
import static io.musika.notifier.domain.model.store.SampleArtists.GIBBS_MASTER;
import static io.musika.notifier.domain.model.store.SampleArtists.MERSEL;
import static io.musika.notifier.domain.model.store.SampleArtists.MOONRACK;
import static io.musika.notifier.domain.model.store.SampleArtists.ODEN;
import static io.musika.notifier.domain.model.store.SampleArtists.PIERROTECHNIQUE;
import static io.musika.notifier.domain.model.store.SampleArtists.SETIAWAN;
import static io.musika.notifier.domain.model.store.SampleArtists.VOICE_OF_SYLENTH;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.musika.notifier.domain.model.shared.kernel.Track;
import io.musika.notifier.domain.model.shared.kernel.TrackId;

/**
 * Sample tracks, for test purposes.
 */
public class SampleTracks {

	public static final Track MOONRACK_OBSIDIAN_SKY_ORIGINAL_MIX =
			new Track.Builder(MOONRACK, "Obsidian Sky")
					.build();

	public static final Track MOONRACK_OBSIDIAN_SKY_SETIAWAN_REMIX =
			new Track.Builder(MOONRACK, "Obsidian Sky")
					.addRemixer(SETIAWAN)
					.build();

	public static final Track MOONRACK_OBSIDIAN_SKY_GIBBS_MASTER_REMIX =
			new Track.Builder(MOONRACK, "Obsidian Sky")
					.addRemixer(GIBBS_MASTER)
					.build();

	public static final Track MOONRACK_OBSIDIAN_SKY_EKLPX_REMIX =
			new Track.Builder(MOONRACK, "Obsidian Sky")
					.addRemixer(EKLPX)
					.build();

	public static final Track MOONRACK_OBSIDIAN_SKY_MERSEL_REMIX =
			new Track.Builder(MOONRACK, "Obsidian Sky")
					.addRemixer(MERSEL)
					.build();

	public static final Track MOONRACK_OBSIDIAN_SKY_DJ_PANGOLIN_REMIX =
			new Track.Builder(MOONRACK, "Obsidian Sky")
					.addRemixer(DJ_PANGOLIN)
					.build();

	public static final Track DUST_YARD_ROOTED_ORIGINAL_MIX =
			new Track.Builder(DUST_YARD, "Rooted")
					.build();

	public static final Track DUST_YARD_MYSTIC_PIANO_MIX =
			new Track.Builder(DUST_YARD, "Mystic")
					.setMixName("Piano Mix")
					.build();

	public static final Track DUST_YARD_MYSTIC_ORIGINAL_MIX =
			new Track.Builder(DUST_YARD, "Mystic")
					.build();

	public static final Track DUST_YARD_ROOTED_VOICE_OF_SYLENTH_REMIX =
			new Track.Builder(DUST_YARD, "Rooted")
					.addRemixer(VOICE_OF_SYLENTH)
					.build();

	public static final Track DUST_YARD_ROOTED_PIERROTECHNIQUE_REMIX =
			new Track.Builder(DUST_YARD, "Rooted")
					.addRemixer(PIERROTECHNIQUE)
					.build();

	public static final Track ODEN_POINT_G_ORIGINAL_MIX =
			new Track.Builder(ODEN, "Point G")
					.build();

	public static final Track ODEN_VAUDOU_ORIGINAL_MIX =
			new Track.Builder(ODEN, "Vaudou")
					.build();

	public static final Track ODEN_ZIGGY_S_JUNGLE_ORIGINAL_MIX =
			new Track.Builder(ODEN, "Ziggy's Jungle")
					.build();

	public static final Track ODEN_ZIGGY_S_JUNGLE_MOONRACK_REMIX =
			new Track.Builder(ODEN, "Ziggy's Jungle")
					.addRemixer(MOONRACK)
					.build();

	public static final Map<TrackId, Track> ALL = new HashMap<>();

	static {
		for (Field field : SampleTracks.class.getDeclaredFields()) {
			if (field.getType().equals(Store.class)) {
				try {
					Track track = (Track) field.get(null);
					ALL.put(track.identity(), track);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static List<Track> getAll() {
		return new ArrayList<>(ALL.values());
	}

}
