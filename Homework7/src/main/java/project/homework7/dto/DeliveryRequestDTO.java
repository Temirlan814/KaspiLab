package project.homework7.dto;

import java.util.UUID;

public record DeliveryRequestDTO(
        UUID productId,
        String address
) {
}
