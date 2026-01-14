package project.homework6.dto;

import java.util.UUID;

public record DeliveryRequest(
        UUID productId,
        String address
) {
}
