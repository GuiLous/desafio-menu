package com.guilou.desafiomenu.dtos;

import java.util.UUID;

public record ProductDTO(
        String title,
        String description,
        String ownerId,
        Integer price,
        String categoryId
) {
}
