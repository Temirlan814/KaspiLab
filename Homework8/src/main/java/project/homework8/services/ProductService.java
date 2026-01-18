package project.homework8.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import project.homework8.clients.DeliveryClient;
import project.homework8.dto.CreateProductDTO;
import project.homework8.dto.DeliveryRequestDTO;
import project.homework8.dto.DeliveryResponseDTO;
import project.homework8.dto.ProductDTO;
import project.homework8.exceptions.ProductNotFoundException;
import project.homework8.models.Product;
import project.homework8.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DeliveryClient deliveryClient;

    public ProductService(ProductRepository productRepository, DeliveryClient deliveryClient) {
        this.productRepository = productRepository;
        this.deliveryClient = deliveryClient;
    }

    public ProductDTO createProduct(CreateProductDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setAddress(dto.address());
        Product savedProduct = productRepository.save(product);
        
        if (dto.address() != null && !dto.address().isEmpty()) {
            try {
                DeliveryRequestDTO deliveryRequest = new DeliveryRequestDTO(
                        savedProduct.getId(),
                        dto.address()
                );
                DeliveryResponseDTO deliveryResponse = deliveryClient.createDelivery(deliveryRequest);
                System.out.println("Delivery created: " + deliveryResponse);
            } catch (Exception e) {
                System.err.println("Failed to create delivery: " + e.getMessage());
            }
        }
        
        return toDTO(savedProduct);
    }

    public ProductDTO updateProduct(UUID id, CreateProductDTO dto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        existingProduct.setName(dto.name());
        existingProduct.setPrice(dto.price());
        existingProduct.setAddress(dto.address());
        Product updatedProduct = productRepository.save(existingProduct);
        return toDTO(updatedProduct);
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found: " + id);
        }
        productRepository.deleteById(id);
    }

    public List<ProductDTO> findAllProducts() throws InterruptedException {
        Thread.sleep(30000);
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //тут использую кастомный Executor
    @Async("productExecutor")
    public CompletableFuture<List<ProductDTO>> findAllProductsAsync() throws InterruptedException {
        Thread.sleep(30000);
        List<ProductDTO> list = productRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
        return CompletableFuture.completedFuture(list);
    }


    public ProductDTO findProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        return toDTO(product);
    }

    private ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getAddress());
    }

}
