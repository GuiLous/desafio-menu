package com.guilou.desafiomenu.models;

import com.guilou.desafiomenu.dtos.ProductDTO;
import com.guilou.desafiomenu.enums.MessageType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class ProductModel {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String category;

    public ProductModel(ProductDTO productDTO){
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.ownerId = productDTO.ownerId();
        this.price = productDTO.price();
        this.category = productDTO.categoryId();
    }

    public String getStringJSON(MessageType type) {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("price", this.price);
        json.put("categoryId", this.category);
        json.put("type", type.getType());

        return json.toString();
    }
}

