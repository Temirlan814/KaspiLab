package project.HomeWork9Delivery.dto;

import java.util.UUID;

public record DeliveryRequest(
        UUID productId,
        String address
) {
}
