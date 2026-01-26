package project.HomeWork10.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public record DeliveryDTO(
        UUID id,
        UUID productId,
        String address,
        String status,
        LocalDateTime createdAt
) {}
