package project.HomeWork10.clients;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import project.HomeWork10.dto.ProductDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient.Builder builder,
                         @Value("${product.service.url}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    public Mono<ProductDTO> getById(UUID id) {
        return webClient.get()
                .uri("/product/{id}", id)   // <-- ВАЖНО: без s
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }
}
