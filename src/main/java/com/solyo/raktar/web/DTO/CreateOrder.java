package com.solyo.raktar.web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrder {
    private long productId;
    private int quantity;
}
