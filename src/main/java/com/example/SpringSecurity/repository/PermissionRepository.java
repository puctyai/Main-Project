package com.example.SpringSecurity.repository;

import com.example.SpringSecurity.model.Permission;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Integer> {


    @Query("SELECT p FROM Permission p WHERE p.role = 'ROLE_STUDENT'")
    Permission getStandartPermission();
}