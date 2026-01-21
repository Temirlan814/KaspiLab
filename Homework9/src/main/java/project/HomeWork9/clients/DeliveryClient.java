package project.HomeWork9.clients;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import project.HomeWork9.dto.DeliveryRequestDTO;
import project.HomeWork9.dto.DeliveryResponseDTO;
import reactor.core.publisher.Mono;


@Component
public class DeliveryClient {

    private final WebClient webClient;

    public DeliveryClient(WebClient.Builder builder,
                             @Value("${delivery.service.url}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    public Mono<DeliveryResponseDTO> createDelivery(DeliveryRequestDTO request) {
        return webClient.post()
                .uri("/delivery")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DeliveryResponseDTO.class);
    }
}

