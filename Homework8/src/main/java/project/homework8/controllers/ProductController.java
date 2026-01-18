package project.homework8.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.homework8.dto.CreateProductDTO;
import project.homework8.dto.ProductDTO;
import project.homework8.services.ProductService;

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


    // ограничел поток томката до одного чтобы показать чтобы мой async работает
    @GetMapping("/async")
    public CompletableFuture<List<ProductDTO>> get() throws InterruptedException {
        return productService.findAllProductsAsync();
    }

    @GetMapping("/sync")
    public List<ProductDTO> getAll() throws InterruptedException {
        return productService.findAllProducts();
    }
}
