package com.vakya.bakendprojet.services;

import com.vakya.bakendprojet.models.User;

import java.util.Optional;

public interface UserService {

    User register(User user);

    User findByUsername(String username);
}
