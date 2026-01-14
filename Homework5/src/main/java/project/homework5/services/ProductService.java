package project.homework5.services;

import org.springframework.stereotype.Service;
import project.homework5.clients.DeliveryClient;
import project.homework5.dto.CreateProductDTO;
import project.homework5.dto.DeliveryRequestDTO;
import project.homework5.dto.DeliveryResponseDTO;
import project.homework5.dto.ProductDTO;
import project.homework5.exceptions.ProductNotFoundException;
import project.homework5.models.Product;
import project.homework5.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;
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

    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
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
