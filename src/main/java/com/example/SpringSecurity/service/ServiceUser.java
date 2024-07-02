package com.example.SpringSecurity.service;


import com.example.SpringSecurity.model.Permission;
import com.example.SpringSecurity.model.User;
import com.example.SpringSecurity.repository.PermissionRepository;
import com.example.SpringSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }

    public String addUser(User user, String rePassword) {
        User user1 = userRepository.findByEmail(user.getEmail());

        if(user1 != null) {
            return "EmailNotValid";
        }

        if(!user.getPassword().equals(rePassword)){
            return "PasswordIncorrect";
        }

        user.setPassword(passwordEncoder.encode(rePassword));

        Permission permission = permissionRepository.getStandartPermission();

        user.setPermissions(List.of(permission));

        userRepository.save(user);

        return "SuccessAddUser";

    }


    public String changePass(String currentPass, String newPass, String reNewPass) {
        if(!passwordEncoder.matches(currentPass, getCurrentUser().getPassword())){
            return "PasswordIncorrect";
        }

        if(!newPass.equals(reNewPass)){
            return "PasswordsDoNotMatch";
        }

        getCurrentUser().setPassword(passwordEncoder.encode(newPass));

        userRepository.save(getCurrentUser());

        return "Success";
    }
}