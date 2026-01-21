package project.HomeWork9.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table(name = "products")
public class Product {

    @Id
    private UUID id;

    @Column("name")
    private String name;

    @Column("price")
    private Integer price;

    @Column("address")
    private String address;

    public Product() {
    }

    public Product(UUID id, String name, Integer price, String address) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
