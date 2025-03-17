package com.crud.jpa_query_orders_10_03.entity;

import com.crud.jpa_query_orders_10_03.entity.abstracts.AuditableEntity;
import com.crud.jpa_query_orders_10_03.enumeration.ProductCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProductCategory category;

    @Column(name = "price")
    private Double price;

    @Column(name = "deleted")
    private Boolean deleted = false;


    public Product() {
    }

    public Product(Long id, String name, ProductCategory category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
