package project.HomeWork10.dto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.UUID;

@SecurityRequirement(name = "basicAuth")
public record ProductDTO( UUID id, String name, Integer price, String address) {}
