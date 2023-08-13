package com.example.test2.services;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.UserIn;
import com.example.test2.model.dtos.UserOut;

import java.util.List;

public interface UserService {

    List<UserOut> getAll(Integer pageCount);

    UserOut create(UserIn model);

    void deleteById(Long id) throws CustomException;

    UserOut getById(Long id) throws CustomException;

    void update(Long userId, UserIn user);
}
