package com.example.workpraktika.model;

import lombok.Getter;

@Getter
public enum Role {
    USER("Пользователь"),
    ADMIN("Администратор");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}