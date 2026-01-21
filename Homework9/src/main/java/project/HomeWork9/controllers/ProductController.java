package project.HomeWork9.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.*;
import project.HomeWork9.dto.CreateProductDTO;
import project.HomeWork9.dto.ProductDTO;
import project.HomeWork9.services.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Mono<ProductDTO> getProduct(@PathVariable UUID id) {
        return productService.findProductById(id);
    }

    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "basicAuth")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDTO> createProduct(@RequestBody CreateProductDTO dto) {
        return productService.createProduct(dto);
    }

    @SecurityRequirement(name = "basicAuth")
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public Mono<ProductDTO> updateProduct(@RequestBody CreateProductDTO dto, @PathVariable UUID id) {
        return productService.updateProduct(id, dto);
    }

    @SecurityRequirement(name = "basicAuth")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable UUID id) {
        return productService.deleteProduct(id);
    }

    @GetMapping()
    public Flux<ProductDTO> getAllProducts() {
        return productService.findAllProducts();
    }


}
