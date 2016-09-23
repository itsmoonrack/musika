package io.musika.notifier.domain.model.store;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sample stores, for test purposes.
 */
public final class SampleStores {

	public static final Store TRAXSOURCE = new Store(safeURI("http://www.traxsource.com"), "Traxsource");
	public static final Store AMAZON = new Store(safeURI("http://www.amazonmp3.com"), "Amazon MP3");
	public static final Store BEATPORT = new Store(safeURI("http://www.beatport.com"), "Beatport");
	public static final Store DEEZER = new Store(safeURI("http://www.deezer.com"), "Deezer");
	public static final Store GOOGLE_PLAY = new Store(safeURI("http://play.google.com"), "Google Play");
	public static final Store APPLE_MUSIC = new Store(safeURI("http://www.apple.com/music"), "Apple Music");
	public static final Store ITUNES = new Store(safeURI("http://www.itunes.com"), "iTunes");
	public static final Store WHAT_PEOPLE_PLAY = new Store(safeURI("http://www.whatpeopleplay.com"), "What People Play");
	public static final Store BLACK_MARKET_RECORDS = new Store(safeURI("http://www.blackmarket.co.uk/#/"), "Black Market Records");
	public static final Store CLONE_DIGITAL = new Store(safeURI("https://clone.nl/digital"), "Clone Digital");
	public static final Store JUNO_DOWNLOAD = new Store(safeURI("http://www.junodownload.com"), "Juno Download");
	public static final Store MIX_DJ = new Store(safeURI("http://www.mix.dj"), "Mix.dj");
	public static final Store SHAZAM = new Store(safeURI("http://www.shazam.com"), "Shazam");
	public static final Store SPOTIFY = new Store(safeURI("http://www.spotify.com"), "Spotify");
	public static final Store YOUTUBE = new Store(safeURI("http://www.youtube.com"), "YouTube");

	public static final Map<URI, Store> ALL = new HashMap<>();

	static {
		for (Field field : SampleStores.class.getDeclaredFields()) {
			if (field.getType().equals(Store.class)) {
				try {
					Store store = (Store) field.get(null);
					ALL.put(store.uriCode(), store);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static List<Store> getAll() {
		return new ArrayList<>(ALL.values());
	}

	public static Store lookup(URI uriCode) {
		return ALL.get(uriCode);
	}

	private static URI safeURI(String str) {
		try {
			return new URI(str);
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
