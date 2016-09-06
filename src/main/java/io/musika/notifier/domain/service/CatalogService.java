package io.musika.notifier.domain.service;

import java.util.List;

import io.musika.notifier.domain.model.notifier.Catalog;
import io.musika.notifier.domain.model.notifier.TrackSpecification;

/**
 * Catalog service.
 */
public interface CatalogService {

	/**
	 * @param trackSpecification track specification
	 * @return A list of catalog that satisfy the specification. May be an empty list if no catalog is found.
	 */
	List<Catalog> fetchCatalogForSpecification(TrackSpecification trackSpecification);

}
