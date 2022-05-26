package com.solyo.raktar.web.DTO;

import com.solyo.raktar.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProduct {
        private long id;
        private String name;
        private String description;
        private int price;
        private int quantity;
        private String category;
}
