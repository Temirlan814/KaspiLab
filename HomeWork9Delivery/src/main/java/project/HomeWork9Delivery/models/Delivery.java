package project.HomeWork9Delivery.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;


@Table(name = "deliveries")
public class Delivery {

    @Id
    private UUID id;

    @Column("product_id")
    private UUID productId;

    @Column("address")
    private String address;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("status")
    private String status;

    public Delivery() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Delivery(UUID productId, String address) {
        this();
        this.productId = productId;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
