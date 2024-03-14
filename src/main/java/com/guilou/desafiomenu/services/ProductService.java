package com.guilou.desafiomenu.services;

import com.guilou.desafiomenu.dtos.MessageDTO;
import com.guilou.desafiomenu.dtos.ProductDTO;
import com.guilou.desafiomenu.enums.MessageType;
import com.guilou.desafiomenu.exceptions.category.CategoryNotFoundException;
import com.guilou.desafiomenu.exceptions.product.ProductNotFoundException;
import com.guilou.desafiomenu.models.ProductModel;
import com.guilou.desafiomenu.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AwsSnsService snsService;

    public ProductModel create(ProductDTO productData) {
        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        var productModel = new ProductModel(productData);


        var productCreated = this.productRepository.save(productModel);

        this.snsService.publish(new MessageDTO(productModel.getStringJSON(MessageType.PRODUCT)));

        return productCreated;
    }

    public List<ProductModel> getAll() {
        return this.productRepository.findAll();
    }

    public ProductModel update(String id, ProductDTO productData) {
        var product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if(productData.categoryId() != null) {
            this.categoryService.getById(productData.categoryId())
                .orElseThrow(ProductNotFoundException::new);
            product.setCategory(productData.categoryId());
        }

        if (!productData.title().isEmpty()) product.setTitle(productData.title());
        if (!productData.description().isEmpty()) product.setDescription(productData.description());
        if (!productData.ownerId().isEmpty()) product.setOwnerId(productData.ownerId());
        if(productData.price() != null) product.setPrice(productData.price());

        this.productRepository.save(product);

        this.snsService.publish(new MessageDTO(product.getStringJSON(MessageType.PRODUCT)));

        return product;
    }

    public void delete(String id) {
        var product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(product);

        this.snsService.publish(new MessageDTO(product.getStringJSON(MessageType.DELETE_PRODUCT)));
    }
}
