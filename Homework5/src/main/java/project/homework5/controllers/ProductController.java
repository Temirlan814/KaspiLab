package project.homework5.controllers;

import org.springframework.web.bind.annotation.*;
import project.homework5.dto.CreateProductDTO;
import project.homework5.dto.ProductDTO;
import project.homework5.services.ProductService;

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
    public ProductDTO createProduct(@RequestBody CreateProductDTO dto) {
        return productService.createProduct(dto);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody CreateProductDTO dto, @PathVariable UUID id) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @GetMapping
    public List<ProductDTO> findAllProducts() {
        return productService.findAllProducts();
    }
}
