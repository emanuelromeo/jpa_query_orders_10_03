package com.crud.jpa_query_orders_10_03.controller;

import com.crud.jpa_query_orders_10_03.entity.Product;
import com.crud.jpa_query_orders_10_03.enumeration.ProductCategory;
import com.crud.jpa_query_orders_10_03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Selects all products.
     *
     * @return a response entity containing a page of products.
     */
    @GetMapping("/select-all")
    public ResponseEntity<Page<Product>> findAllProducts(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {

        Page<Product> products = productService.findAllProducts(pageNumber, pageSize);
        return ResponseEntity.ok(products);

    }

    /**
     * Returns a product by its id.
     *
     * @param id
     * @return a response entity containing the product with the given id or ResponseEntity.notFound() if none found.
     */
    @GetMapping("/select-by-id/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {

        Optional<Product> optionalProduct = productService.findProductById(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Saves the given product.
     *
     * @param product
     * @return a response entity containing the saved product.
     */
    @PostMapping("/create")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);

    }

    /**
     * Updates a product by its id with values from the given product.
     *
     * @param id
     * @param updatedProduct
     * @return a response entity containing the updated product or ResponseEntity.notFound() if none found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

        Optional<Product> optionalProduct = productService.updateProduct(id, updatedProduct);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Soft deletes a product by its id.
     *
     * @param id
     * @return a response entity containing the deleted product or ResponseEntity.notFound() if none found.
     */
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {

        Optional<Product> optionalProduct = productService.deleteProductById(id);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Selects all products which category is the given category and which price is less than the given price.
     * @param category
     * @param maxPrice
     * @return a response entity containing a page of products.
     */
    @GetMapping("/select-by-category-and-price-less-than")
    public ResponseEntity<Page<Product>> findProductByCategoryAndPriceLessThan(
            @RequestParam ProductCategory category,
            @RequestParam Double maxPrice,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {

        Page<Product> products = productService.findProductsByCategoryAndPriceLessThan(category, maxPrice, pageNumber, pageSize);
        return ResponseEntity.ok(products);

    }

    @GetMapping("/select-by-name/{name}")
    public ResponseEntity<List<Product>> findProductByName(@PathVariable String name) {
        List<Product> products = productService.findProductByName(name);
        return ResponseEntity.ok(products);
    }
}

