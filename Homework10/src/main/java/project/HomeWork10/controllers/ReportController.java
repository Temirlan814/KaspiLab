package project.HomeWork10.controllers;

import org.springframework.web.bind.annotation.*;

import project.HomeWork10.dto.ReportDTO;
import project.HomeWork10.dto.ReportRequest;
import project.HomeWork10.services.ReportService;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/product-deliveries")
    public Mono<List<ReportDTO>> build(@RequestBody ReportRequest request) {
        return reportService.buildReport(request.productIds());
    }
}
