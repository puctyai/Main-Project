package com.example.SpringSecurity.repository;

import com.example.SpringSecurity.model.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
