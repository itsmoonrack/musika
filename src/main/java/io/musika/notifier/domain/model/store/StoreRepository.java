package io.musika.notifier.domain.model.store;

import java.net.URI;
import java.util.List;

public interface StoreRepository {

	/**
	 * Finds a store using given uriCode.
	 *
	 * @param uriCode URI code
	 * @return Store
	 */
	Store find(URI uriCode);

	/**
	 * Finds all stores.
	 *
	 * @return All stores
	 */
	List<Store> findAll();

}
