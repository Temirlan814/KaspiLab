package project.HomeWork11Delivery.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeliveryResponse(
        UUID id,
        UUID productId,
        String address,
        String status,
        LocalDateTime createdAt
) {
}
