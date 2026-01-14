package project.homework6.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.homework6.dto.DeliveryRequest;
import project.homework6.dto.DeliveryResponse;
import project.homework6.services.DeliveryService;

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
    public DeliveryResponse createDelivery(@RequestBody DeliveryRequest request) {
        return deliveryService.createDelivery(request);
    }

    @GetMapping
    public List<DeliveryResponse> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public DeliveryResponse getDeliveryById(@PathVariable UUID id) {
        return deliveryService.getDeliveryById(id);
    }
}
