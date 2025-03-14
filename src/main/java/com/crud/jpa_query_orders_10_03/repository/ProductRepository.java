package com.crud.jpa_query_orders_10_03.repository;

import com.crud.jpa_query_orders_10_03.entity.Product;
import com.crud.jpa_query_orders_10_03.enumeration.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryAndPriceLessThan(ProductCategory category, Double maxPrice, Pageable pageable);

    @Query("select p from Product p where p.name = :name")
    List<Product> findByName(@Param("name") String name);
}
