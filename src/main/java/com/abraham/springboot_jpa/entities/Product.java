package com.abraham.springboot_jpa.entities;

import com.abraham.springboot_jpa.validation.IsExistDb;
import com.abraham.springboot_jpa.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsRequired // (message = "{IsRequired.product.name}")
    @Size(min = 3, max = 20)
    private String name;

    @Min(500)
    @NotNull
    private Integer price;

    @IsRequired // (message = "{IsRequired.product.description}")
    private String description;

    @IsRequired // (message = "{IsRequired.product.sku}")
    @IsExistDb // (message = "{IsExistDb.product.sku}")
    private String sku;

}
