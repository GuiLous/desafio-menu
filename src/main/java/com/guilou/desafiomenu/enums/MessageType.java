package com.guilou.desafiomenu.enums;

import lombok.Getter;

@Getter
public enum MessageType {
    PRODUCT("product"),
    DELETE_PRODUCT("delete-product"),
    CATEGORY("category"),
    DELETE_CATEGORY("delete-category");

    private final String type;

    MessageType(String type) {
        this.type = type;
    }
}
