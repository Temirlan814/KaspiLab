package project.HomeWork9Delivery.services;

import org.springframework.stereotype.Service;
import project.HomeWork9Delivery.dto.DeliveryRequest;
import project.HomeWork9Delivery.dto.DeliveryResponse;
import project.HomeWork9Delivery.models.Delivery;
import project.HomeWork9Delivery.repositories.DeliveryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Mono<DeliveryResponse> createDelivery(DeliveryRequest request) {
        Delivery delivery = new Delivery();
        delivery.setProductId(request.productId());
        delivery.setAddress(request.address());

        return deliveryRepository.save(delivery)
                .map(this::toResponse);
    }

    public Flux<DeliveryResponse> getAllDeliveries() {
        return deliveryRepository.findAll()
                .map(this::toResponse);
    }

    public Mono<DeliveryResponse> getDeliveryById(UUID id) {
        return deliveryRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Delivery not found: " + id)))
                .map(this::toResponse);
    }
    public Mono<DeliveryResponse> getDeliveryByProductId(UUID id) {
        return deliveryRepository.findByProductId(id)  // Mono<Delivery>
                .switchIfEmpty(Mono.error(new RuntimeException("Delivery not found for product: " + id)));
    }

    private DeliveryResponse toResponse(Delivery delivery) {
        return new DeliveryResponse(
                delivery.getId(),
                delivery.getProductId(),
                delivery.getAddress(),
                delivery.getStatus(),
                delivery.getCreatedAt()
        );
    }

}
