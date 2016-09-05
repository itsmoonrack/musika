package io.musika.notifier.domain.model.notifier;

import io.musika.notifier.domain.model.shared.kernel.TrackId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, TrackId> {
}
