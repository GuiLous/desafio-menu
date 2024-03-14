package com.guilou.desafiomenu.services;

import com.guilou.desafiomenu.dtos.CategoryDTO;
import com.guilou.desafiomenu.dtos.MessageDTO;
import com.guilou.desafiomenu.enums.MessageType;
import com.guilou.desafiomenu.exceptions.category.CategoryNotFoundException;
import com.guilou.desafiomenu.models.CategoryModel;
import com.guilou.desafiomenu.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AwsSnsService snsService;

    public CategoryModel create(CategoryDTO categoryDTO) {
        var categoryModel = new CategoryModel(categoryDTO);

        var categoryCreated = this.categoryRepository.save(categoryModel);

        this.snsService.publish(new MessageDTO(categoryModel.getStringJSON(MessageType.CATEGORY)));

        return  categoryCreated;
    }

    public List<CategoryModel> getAll() {
        return this.categoryRepository.findAll();
    }

    public CategoryModel update(String id, CategoryDTO categoryData) {
        var category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        this.categoryRepository.save(category);

        this.snsService.publish(new MessageDTO(category.getStringJSON(MessageType.CATEGORY)));

        return category;
    }

    public void delete(String id) {
        var category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        this.categoryRepository.delete(category);

        this.snsService.publish(new MessageDTO(category.getStringJSON(MessageType.DELETE_CATEGORY)));
    }

    public Optional<CategoryModel> getById(String id) {
        return this.categoryRepository.findById(id);
    }
}
