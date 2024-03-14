package com.guilou.desafiomenu.controllers;

import com.guilou.desafiomenu.dtos.ProductDTO;
import com.guilou.desafiomenu.models.ProductModel;
import com.guilou.desafiomenu.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> create(@RequestBody ProductDTO productData) {
        var createdProduct = this.productService.create(productData);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAll() {
        List<ProductModel> products = this.productService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable String id, @RequestBody ProductDTO productData) {
        var updatedProduct = this.productService.update(id, productData);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        this.productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
