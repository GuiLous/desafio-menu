package com.guilou.desafiomenu.repositories;

import com.guilou.desafiomenu.models.CategoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryModel, String> {
}
