package com.solyo.raktar.web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCategory {
    private String name;
    private String parentCategory;
}
