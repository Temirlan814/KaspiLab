package project.homework7.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeliveryResponseDTO(
        UUID id,
        UUID productId,
        String address,
        String status,
        LocalDateTime createdAt
) {
}
