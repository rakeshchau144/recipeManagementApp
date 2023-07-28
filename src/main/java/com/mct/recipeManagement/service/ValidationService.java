package com.mct.recipeManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    @Autowired
    UserService userService;
    public boolean authenticate(Long userId) {
        return userService.authenticate(userId);
    }
}
