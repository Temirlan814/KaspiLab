package project.homework8.dto;

import java.util.UUID;

public record DeliveryRequestDTO(
        UUID productId,
        String address
) {
}
