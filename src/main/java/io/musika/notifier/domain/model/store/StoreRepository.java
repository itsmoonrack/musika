package io.musika.notifier.domain.model.store;

import org.springframework.data.repository.CrudRepository;

import java.net.URI;

public interface StoreRepository extends CrudRepository<Store, URI> {

}
