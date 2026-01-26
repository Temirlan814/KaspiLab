package project.HomeWork9Delivery.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.HomeWork9Delivery.dto.DeliveryRequest;
import project.HomeWork9Delivery.dto.DeliveryResponse;
import project.HomeWork9Delivery.services.DeliveryService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<DeliveryResponse> createDelivery(@RequestBody DeliveryRequest request) {
        return deliveryService.createDelivery(request);
    }

    @GetMapping
    public Flux<DeliveryResponse> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public Mono<DeliveryResponse> getDeliveryById(@PathVariable UUID id) {
        return deliveryService.getDeliveryById(id);
    }
    @GetMapping("/product/{productId}")
    public Mono<DeliveryResponse> getDeliveryByProductId(@PathVariable UUID productId) {
        return deliveryService.getDeliveryByProductId(productId);
    }
}
