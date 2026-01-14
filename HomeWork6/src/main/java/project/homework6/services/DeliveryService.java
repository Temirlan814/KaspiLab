package project.homework6.services;

import org.springframework.stereotype.Service;
import project.homework6.dto.DeliveryRequest;
import project.homework6.dto.DeliveryResponse;
import project.homework6.models.Delivery;
import project.homework6.repositories.DeliveryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeliveryService {
    
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public DeliveryResponse createDelivery(DeliveryRequest request) {
        Delivery delivery = new Delivery();
        delivery.setProductId(request.productId());
        delivery.setAddress(request.address());
        
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return toResponse(savedDelivery);
    }

    public List<DeliveryResponse> getAllDeliveries() {
        return deliveryRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DeliveryResponse getDeliveryById(UUID id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found: " + id));
        return toResponse(delivery);
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
