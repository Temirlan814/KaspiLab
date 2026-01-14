package project.homework5.dto;

import java.util.UUID;

public record DeliveryRequestDTO(
        UUID productId,
        String address
) {
}
