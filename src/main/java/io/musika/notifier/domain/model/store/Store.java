package io.musika.notifier.domain.model.store;

import static org.apache.commons.lang3.Validate.notNull;

import java.net.URI;

import io.musika.notifier.domain.model.shared.Entity;

/**
 * A store is our model representing an entity who can sell releases of a track, either digitally or physically.
 *
 * It is uniquely identified by a URI code.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public final class Store implements Entity<Store, URI> {

	private URI uriCode;
	private String name;

	/**
	 * Special Store object that marks an unknown store.
	 */
	public static final Store UNKNOWN = new Store();

	/**
	 * Package-level constructor, visible for test only.
	 *
	 * @param uriCode	URI code
	 * @param name		Store name
	 */
	Store(final URI uriCode, final String name) {
		notNull(uriCode);
		notNull(name);

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

	@Override
	public URI identity() {
		return uriCode;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Store other = (Store) o;

		return sameIdentityAs(other);
	}

	@Override
	public boolean sameIdentityAs(final Store other) {
		return other != null && uriCode.equals(other.uriCode);
	}

	@Override
	public int hashCode() {
		return uriCode.hashCode();
	}

	protected Store() {
		// Needed by Hibernate
	}

	private Long id;

}
