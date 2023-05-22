package com.example.healthcaremanagement.service.UserImpl;

import com.example.healthcaremanagement.entity.User;

public interface UserService {
    User createUser(User User);

    void saveUser(User user);
}

