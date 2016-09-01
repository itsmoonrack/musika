package io.musika.notifier.domain.model.shared.kernel;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrackRepository extends PagingAndSortingRepository<Track, TrackId> {

}
