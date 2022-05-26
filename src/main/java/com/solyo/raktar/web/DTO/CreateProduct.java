package com.solyo.raktar.web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProduct {
    private String name;
    private String category;
    private String description;
    private int quantity;
    private int price;
}
