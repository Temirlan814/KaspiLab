package project.HomeWork10.clients;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import project.HomeWork10.dto.DeliveryDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Component
public class DeliveryClient {

    private final WebClient webClient;

    public DeliveryClient(WebClient.Builder builder,
                             @Value("${delivery.service.url}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    public Mono<DeliveryDTO> getById(UUID id) {
        return webClient.get()
                .uri("/delivery/product/{id}", id)
                .retrieve()
                .bodyToMono(DeliveryDTO.class);
    }
}

