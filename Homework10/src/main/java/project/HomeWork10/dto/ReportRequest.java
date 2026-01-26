package project.HomeWork10.dto;

import java.util.List;
import java.util.UUID;

public record ReportRequest(
    List<UUID> productIds
) {}
