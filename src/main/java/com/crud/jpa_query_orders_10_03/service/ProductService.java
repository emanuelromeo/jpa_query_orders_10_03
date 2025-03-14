package com.crud.jpa_query_orders_10_03.service;

import com.crud.jpa_query_orders_10_03.entity.Book;
import com.crud.jpa_query_orders_10_03.entity.Product;
import com.crud.jpa_query_orders_10_03.enumeration.ProductCategory;
import com.crud.jpa_query_orders_10_03.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Selects all products.
     *
     * @return a page of products.
     */
    public Page<Product> findAllProducts(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

    /**
     * Returns a product by its id.
     *
     * @param id
     * @return an optional containing the product with the given id or Optional.empty() if none found.
     */
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Saves the given product.
     *
     * @param product
     * @return the saved product.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates a product by its id with values from the given product.
     *
     * @param id
     * @param updatedProduct
     * @return an optional containing the updated product or Optional.empty() if none found.
     */
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            optionalProduct.get().setName(updatedProduct.getName());
            optionalProduct.get().setCategory(updatedProduct.getCategory());
            optionalProduct.get().setPrice(updatedProduct.getPrice());

            Product savedProduct = productRepository.save(optionalProduct.get());
            return Optional.of(savedProduct);
        }

        return Optional.empty();
    }

    /**
     * Soft deletes a product by its id.
     *
     * @param id
     * @return an optional containing the deleted product or Optional.empty() if none found.
     */
    public Optional<Product> deleteProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            optionalProduct.get().setDeleted(true);

            Product savedProduct = productRepository.save(optionalProduct.get());
            return Optional.of(savedProduct);
        }

        return Optional.empty();
    }

    /**
     * Selects all products which category is the given category and which price is less than the given price.
     *
     * @param category
     * @param maxPrice
     * @return a page of products.
     */
    public Page<Product> findProductsByCategoryAndPriceLessThan(
            ProductCategory category,
            Double maxPrice,
            Integer pageNumber,
            Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByCategoryAndPriceLessThan(category, maxPrice, pageable);

    }

    public List<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

}
