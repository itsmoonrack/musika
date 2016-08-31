package io.musika.notifier.domain.model.release;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (c) Swissquote 31.08.16
 *
 * @author sylvain
 */
public interface ReleaseEventRepository extends JpaRepository<ReleaseEvent, Long> {
}
