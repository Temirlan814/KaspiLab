package project.HomeWork11Delivery.services;

import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import project.HomeWork11Delivery.dto.DeliveryRequest;
import project.HomeWork11Delivery.dto.DeliveryResponse;
import project.HomeWork11Delivery.exceptions.DuplicateDeliveryException;
import project.HomeWork11Delivery.models.Delivery;
import project.HomeWork11Delivery.repositories.DeliveryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ReactiveStringRedisTemplate redis;

    public DeliveryService(DeliveryRepository deliveryRepository, ReactiveStringRedisTemplate redis) {
        this.deliveryRepository = deliveryRepository;
        this.redis = redis;
    }

    public Mono<DeliveryResponse> createDelivery(DeliveryRequest req) {
        String key = "dedup:delivery:" + req.productId() + ":" + req.address().trim().toLowerCase();
        System.out.println("key: " + key);
        return redis.opsForValue()
                .setIfAbsent(key, "1", Duration.ofSeconds(2))  // NX + TTL=2s
                .flatMap(acquired -> {
                    if (Boolean.FALSE.equals(acquired)) {
                        return Mono.error(new DuplicateDeliveryException("Duplicate within 2 seconds"));
                    }
                    Delivery d = new Delivery();
                    d.setProductId(req.productId());
                    d.setAddress(req.address());
                    return deliveryRepository.save(d).map(this::toResponse);
                });
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
