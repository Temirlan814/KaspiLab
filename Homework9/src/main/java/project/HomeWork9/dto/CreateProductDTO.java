package project.HomeWork9.dto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "basicAuth")
public record CreateProductDTO(String name, Integer price, String address) {}
