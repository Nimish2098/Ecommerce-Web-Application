package com.Ecommerce.Backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer stock;

    @Column(length = 1000)
    private String imageUrl;


}
