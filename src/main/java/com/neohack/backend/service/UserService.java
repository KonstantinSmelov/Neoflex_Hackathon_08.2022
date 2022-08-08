package com.neohack.backend.service;

import com.neohack.backend.entity.User;
import com.neohack.backend.exception.AlreadyExistException;
import com.neohack.backend.exception.NoElementException;

public interface UserService {
    void createUser(User user, boolean isStudent) throws AlreadyExistException;
    User getUserByName(String username) throws NoElementException;
    User getUserByEmail(String email) throws NoElementException;
    void setNewPassByEmail(String email, String password) throws NoElementException;

}
