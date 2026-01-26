package project.HomeWork10.services;

import org.springframework.stereotype.Service;

import project.HomeWork10.clients.DeliveryClient;
import project.HomeWork10.clients.ProductClient;
import project.HomeWork10.dto.DeliveryDTO;
import project.HomeWork10.dto.ProductDTO;
import project.HomeWork10.dto.ReportDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private static final String DEFAULT_STATUS = "NO_DELIVERY";

    private final ProductClient productClient;
    private final DeliveryClient deliveryClient;

    public ReportService(ProductClient productClient, DeliveryClient deliveryClient) {
        this.productClient = productClient;
        this.deliveryClient = deliveryClient;
    }

    public Mono<List<ReportDTO>> buildReport(List<UUID> productIds) {

        Mono<List<ProductDTO>> productsMono =
                Flux.fromIterable(productIds)
                        .flatMap(productClient::getById)
                        .collectList();

        Mono<Map<UUID, String>> statusMapMono =
                Flux.fromIterable(productIds)
                        .flatMap(deliveryClient::getById)
                        .map(d -> Map.entry(d.productId(), d.status()))
                        .collectMap(Map.Entry::getKey, Map.Entry::getValue);

        return Mono.zip(productsMono, statusMapMono)
                .map(tuple -> {
                    List<ProductDTO> products = tuple.getT1();
                    Map<UUID, String> statusMap = tuple.getT2();

                    return products.stream()
                            .map(p -> new ReportDTO(
                                    p.id(),
                                    p.name(),
                                    statusMap.getOrDefault(p.id(), "NO_DELIVERY")
                            ))
                            .toList();
                });
    }
}
