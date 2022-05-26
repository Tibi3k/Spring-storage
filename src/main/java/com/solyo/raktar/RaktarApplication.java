package com.solyo.raktar;

import com.solyo.raktar.dao.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class RaktarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaktarApplication.class, args);
    }

}
