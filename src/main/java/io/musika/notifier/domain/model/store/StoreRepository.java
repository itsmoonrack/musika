package io.musika.notifier.domain.model.store;

import java.net.URI;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import io.musika.notifier.domain.model.release.UnknownStoreException;

public interface StoreRepository extends CrudRepository<Store, URI> {

}
