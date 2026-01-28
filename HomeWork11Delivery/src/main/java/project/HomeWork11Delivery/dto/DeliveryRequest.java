package project.HomeWork11Delivery.dto;

import java.util.UUID;

public record DeliveryRequest(
        UUID productId,
        String address
) {
}
