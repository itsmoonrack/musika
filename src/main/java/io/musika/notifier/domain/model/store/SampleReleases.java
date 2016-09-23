package io.musika.notifier.domain.model.store;

import static io.musika.notifier.domain.model.store.SampleArtists.DUST_YARD;
import static io.musika.notifier.domain.model.store.SampleArtists.MOONRACK;
import static io.musika.notifier.domain.model.store.SampleArtists.ODEN;
import static io.musika.notifier.domain.model.store.SampleArtists.PIERROTECHNIQUE;
import static io.musika.notifier.domain.model.store.SampleRecordLabels.OSCIL8_RECORDINGS;
import static io.musika.notifier.domain.model.store.SampleTracks.DUST_YARD_MYSTIC_ORIGINAL_MIX;
import static io.musika.notifier.domain.model.store.SampleTracks.DUST_YARD_MYSTIC_PIANO_MIX;
import static io.musika.notifier.domain.model.store.SampleTracks.DUST_YARD_ROOTED_PIERROTECHNIQUE_REMIX;
import static io.musika.notifier.domain.model.store.SampleTracks.DUST_YARD_ROOTED_VOICE_OF_SYLENTH_REMIX;
import static io.musika.notifier.domain.model.store.SampleTracks.DUST_YARD_ROOTED_ORIGINAL_MIX;
import static io.musika.notifier.domain.model.store.SampleTracks.MOONRACK_OBSIDIAN_SKY_DJ_PANGOLIN_REMIX;
import static io.musika.notifier.domain.model.store.SampleTracks.MOONRACK_OBSIDIAN_SKY_EKLPX_REMIX;
import static io.musika.notifier.domain.model.store.SampleTracks.MOONRACK_OBSIDIAN_SKY_GIBBS_MASTER_REMIX;
import static io.musika.notifier.domain.model.store.SampleTracks.MOONRACK_OBSIDIAN_SKY_MERSEL_REMIX;
import static io.musika.notifier.domain.model.store.SampleTracks.MOONRACK_OBSIDIAN_SKY_ORIGINAL_MIX;
import static io.musika.notifier.domain.model.store.SampleTracks.MOONRACK_OBSIDIAN_SKY_SETIAWAN_REMIX;
import static io.musika.notifier.domain.model.store.SampleTracks.ODEN_POINT_G_ORIGINAL_MIX;
import static io.musika.notifier.domain.model.store.SampleTracks.ODEN_VAUDOU_ORIGINAL_MIX;
import static io.musika.notifier.domain.model.store.SampleTracks.ODEN_ZIGGY_S_JUNGLE_MOONRACK_REMIX;
import static io.musika.notifier.domain.model.store.SampleTracks.ODEN_ZIGGY_S_JUNGLE_ORIGINAL_MIX;

import io.musika.notifier.domain.model.shared.kernel.Release;
import io.musika.notifier.domain.model.shared.kernel.ReleaseNumber;

/**
 * Sample releases, for test purposes.
 */
public final class SampleReleases {

	/**
	 * Release number OSCIL001 (Obsidian Sky)
	 *
	 * 1. Moonrack - Obsidian Sky (Original Mix)
	 * 2. Moonrack - Obsidian Sky (Setiawan Remix)
	 * 3. Moonrack - Obsidian Sky (Gibb's Master Remix)
	 * 4. Moonrack - Obsidian Sky (EKLPX Remix)
	 * 5. Moonrack - Obsidian Sky (Mersel Remix)
	 * 6. Moonrack - Obsidian Sky (DJ Pangolin Remix)
	 */
	public static final Release OBSIDIAN_SKY =
			new Release.Builder(new ReleaseNumber("OSCIL001"), "Obsidian Sky", OSCIL8_RECORDINGS)
					.addTrack(MOONRACK_OBSIDIAN_SKY_ORIGINAL_MIX)
					.addTrack(MOONRACK_OBSIDIAN_SKY_SETIAWAN_REMIX)
					.addTrack(MOONRACK_OBSIDIAN_SKY_GIBBS_MASTER_REMIX)
					.addTrack(MOONRACK_OBSIDIAN_SKY_EKLPX_REMIX)
					.addTrack(MOONRACK_OBSIDIAN_SKY_MERSEL_REMIX)
					.addTrack(MOONRACK_OBSIDIAN_SKY_DJ_PANGOLIN_REMIX)
					.setReleaseDate("2016-06-15")
					.build();

	/**
	 * Release number OSCIL002 (Mystic Ep)
	 *
	 * 1. Dust Yard - Rooted (Original Mix)
	 * 2. Dust Yard - Mystic (Piano Mix)
	 * 3. Dust Yard - Mystic (Original Mix)
	 * 4. Dust Yard - Rooted (Voice Of Sylenth Remix)
	 * 5. Dust Yard - Rooted (Pierrotechnique Remix)
	 */
	public static final Release MYSTIC_EP =
			new Release.Builder(new ReleaseNumber("OSCIL002"), "Mystic Ep", OSCIL8_RECORDINGS)
					.addTrack(DUST_YARD_ROOTED_ORIGINAL_MIX)
					.addTrack(DUST_YARD_MYSTIC_PIANO_MIX)
					.addTrack(DUST_YARD_MYSTIC_ORIGINAL_MIX)
					.addTrack(DUST_YARD_ROOTED_VOICE_OF_SYLENTH_REMIX)
					.addTrack(DUST_YARD_ROOTED_PIERROTECHNIQUE_REMIX)
					.setReleaseDate("2016-07-15")
					.build();

	/**
	 * Release number OSCIL003 (Mountain Noodles)
	 *
	 * 1. Oden - Point G (Original Mix)
	 * 2. Oden - Vaudou (Piano Mix)
	 * 3. Oden - Ziggy's Jungle (Original Mix)
	 * 4. Oden - Ziggy's Jungle (Moonrack Remix)
	 */
	public static final Release MOUNTAIN_NOODLES =
			new Release.Builder(new ReleaseNumber("OSCIL003"), "Mountain Noodles", OSCIL8_RECORDINGS)
					.addTrack(ODEN_POINT_G_ORIGINAL_MIX)
					.addTrack(ODEN_VAUDOU_ORIGINAL_MIX)
					.addTrack(ODEN_ZIGGY_S_JUNGLE_ORIGINAL_MIX)
					.addTrack(ODEN_ZIGGY_S_JUNGLE_MOONRACK_REMIX)
					.build();

}
