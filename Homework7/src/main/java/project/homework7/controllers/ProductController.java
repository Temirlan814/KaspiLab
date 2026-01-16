package project.homework7.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.homework7.dto.CreateProductDTO;
import project.homework7.dto.ProductDTO;
import project.homework7.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable UUID id) {
        return productService.findProductById(id);
    }

    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "basicAuth")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody CreateProductDTO dto) {
        return productService.createProduct(dto);
    }

    @SecurityRequirement(name = "basicAuth")
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody CreateProductDTO dto, @PathVariable UUID id) {
        return productService.updateProduct(id, dto);
    }

    @SecurityRequirement(name = "basicAuth")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @GetMapping
    public List<ProductDTO> findAllProducts() {
        return productService.findAllProducts();
    }
}
