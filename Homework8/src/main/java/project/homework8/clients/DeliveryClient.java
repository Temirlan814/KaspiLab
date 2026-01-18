package project.homework8.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.homework8.dto.DeliveryRequestDTO;
import project.homework8.dto.DeliveryResponseDTO;

@FeignClient(name = "delivery-service", url = "${delivery.service.url}")
public interface DeliveryClient {
    
    @PostMapping("/delivery")
    DeliveryResponseDTO createDelivery(@RequestBody DeliveryRequestDTO request);
}
