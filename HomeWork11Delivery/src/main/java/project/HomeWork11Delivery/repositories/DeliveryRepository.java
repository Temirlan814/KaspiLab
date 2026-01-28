package project.HomeWork11Delivery.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import project.HomeWork11Delivery.dto.DeliveryResponse;
import project.HomeWork11Delivery.models.Delivery;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface DeliveryRepository extends ReactiveCrudRepository<Delivery, UUID> {
    Mono<DeliveryResponse> findByProductId(UUID productId);

}
