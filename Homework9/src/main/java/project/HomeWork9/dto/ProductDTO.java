package project.HomeWork9.dto;

import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        Integer price,
        String address
) {
}
