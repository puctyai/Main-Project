package com.example.SpringSecurity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "text")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "location")
    private String location;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @PrePersist
    private void createdAt(){
        createdAt=LocalDateTime.now();
    }
    @PreUpdate
    private void updatedAt(){
        updatedAt=LocalDateTime.now();
    }
}
