package com.example.SpringSecurity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDTO {

    private int id;
    private String itemName;
    private String description;
    private int price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
