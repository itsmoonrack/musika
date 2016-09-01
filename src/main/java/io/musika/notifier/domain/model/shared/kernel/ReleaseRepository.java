package io.musika.notifier.domain.model.shared.kernel;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Release repository.
 */
public interface ReleaseRepository extends PagingAndSortingRepository<Release, ReleaseNumber> {
}
