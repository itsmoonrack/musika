package io.musika.notifier.domain.model.store;

import java.net.URI;

/**
 * A store is our model representing an entity who can sell releases of a track, either digitally or physically.
 *
 * It is uniquely identified by a URI code.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public final class Store {

	private URI uriCode;
	private String name;

	Store(final URI uriCode, final String name) {
		this.uriCode = uriCode;
		this.name = name;
	}

	/**
	 * @return URI for this store.
	 */
	public URI uriCode() {
		return uriCode;
	}

	/**
	 * @return Actual name of this store, e.g. "Beatport".
	 */
	public String name() {
		return name;
	}

}
