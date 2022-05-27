package com.solyo.raktar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;

    private int quantity;

    private int totalPrice;

    private String creationTime;

    private String status;

    private String transporterName;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product=" + product.getName() +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", creationTime='" + creationTime + '\'' +
                ", status='" + status + '\'' +
                ", transporterName='" + transporterName + '\'' +
                '}';
    }
}
