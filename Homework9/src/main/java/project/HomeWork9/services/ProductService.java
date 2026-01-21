package project.HomeWork9.services;

import org.springframework.stereotype.Service;
import project.HomeWork9.clients.DeliveryClient;
import project.HomeWork9.dto.CreateProductDTO;
import project.HomeWork9.dto.DeliveryRequestDTO;
import project.HomeWork9.dto.ProductDTO;
import project.HomeWork9.exceptions.ProductNotFoundException;
import project.HomeWork9.models.Product;
import project.HomeWork9.repositories.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DeliveryClient deliveryClient;

    public ProductService(ProductRepository productRepository, DeliveryClient deliveryClient) {
        this.productRepository = productRepository;
        this.deliveryClient = deliveryClient;
    }

    public Mono<ProductDTO> createProduct(CreateProductDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setAddress(dto.address());

        return productRepository.save(product)
                .flatMap(savedProduct -> {
                    if (dto.address() == null || dto.address().isBlank()) {
                        return Mono.just(toDTO(savedProduct));
                    }

                    DeliveryRequestDTO deliveryRequest = new DeliveryRequestDTO(
                            savedProduct.getId(),
                            dto.address()
                    );

                    return deliveryClient.createDelivery(deliveryRequest)
                            // успех: есть resp (DeliveryResponseDTO)
                            .doOnNext(resp -> System.out.println("Delivery created: " + resp))
                            // ошибка: есть e (Throwable)
                            .onErrorResume(e -> {
                                System.err.println("Failed to create delivery: " + e.getMessage());
                                return Mono.empty(); // как try/catch: не валим создание продукта
                            })
                            .thenReturn(toDTO(savedProduct));
                });
    }

    public Mono<ProductDTO> updateProduct(UUID id, CreateProductDTO dto) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found: " + id)))
                .flatMap(existing -> {
                    existing.setName(dto.name());
                    existing.setPrice(dto.price());
                    existing.setAddress(dto.address());
                    return productRepository.save(existing);
                })
                .map(this::toDTO);
    }

    public Mono<Void> deleteProduct(UUID id) {
        return productRepository.existsById(id)
                .flatMap(exists -> exists
                        ? productRepository.deleteById(id)
                        : Mono.error(new ProductNotFoundException("Product not found: " + id)));
    }

    public Flux<ProductDTO> findAllProducts() {
        return productRepository.findAll()
                .map(this::toDTO);
    }

    public Mono<ProductDTO> findProductById(UUID id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found: " + id)))
                .map(this::toDTO);
    }


    private ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getAddress());
    }
}
