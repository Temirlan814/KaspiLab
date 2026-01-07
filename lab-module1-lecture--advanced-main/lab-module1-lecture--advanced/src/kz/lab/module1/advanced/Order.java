package kz.lab.module1.advanced;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

public record Order(long id, double price, LocalDateTime orderDate, Set<String> items ) {
    public Order {
        if(price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        var now = LocalDateTime.now();
        if (!orderDate.isBefore(now)) {
            throw new IllegalArgumentException("orderDate must be in the past");
        }
        items = Set.copyOf(items);

    }
}

