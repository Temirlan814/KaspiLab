package project.HomeWork10.dto;

import java.util.UUID;

public record ReportDTO(
    UUID productId,
    String productName,
    String deliveryStatus
) {}
