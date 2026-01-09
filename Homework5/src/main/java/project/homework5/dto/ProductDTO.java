package project.homework5.dto;

import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        Integer price
) {
}
