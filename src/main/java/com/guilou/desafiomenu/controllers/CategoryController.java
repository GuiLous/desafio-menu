package com.guilou.desafiomenu.controllers;

import com.guilou.desafiomenu.dtos.CategoryDTO;
import com.guilou.desafiomenu.models.CategoryModel;
import com.guilou.desafiomenu.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryModel> create(@RequestBody CategoryDTO categoryData) {
        var createdCategory = this.categoryService.create(categoryData);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAll() {
        List<CategoryModel> categories = this.categoryService.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable String id, @RequestBody CategoryDTO categoryData) {
        var updatedCategory = this.categoryService.update(id, categoryData);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        this.categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
