package com.example.test2.services;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.ProfileIn;
import com.example.test2.model.dtos.ProfileOut;

import java.util.List;

public interface ProfileService {

    List<ProfileOut> getAll();

    ProfileOut create(ProfileIn model);

    void deleteById(Long id) throws CustomException;

    ProfileOut getById(Long id) throws CustomException;
}
