package com.guilou.desafiomenu.models;

import com.guilou.desafiomenu.dtos.CategoryDTO;
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
@Document(collection = "categories")
public class CategoryModel {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public CategoryModel(CategoryDTO categoryDTO){
        this.title = categoryDTO.title();
        this.description = categoryDTO.description();
        this.ownerId = categoryDTO.ownerId();
    }

    public String getStringJSON(MessageType type) {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("type", type.getType());

        return json.toString();
    }
}
