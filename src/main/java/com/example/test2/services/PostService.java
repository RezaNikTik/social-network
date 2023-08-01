package com.example.test2.services;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;

import java.util.List;

public interface PostService {

    List<PostOut> getAll();

    PostOut create(PostIn model);

    void deleteById(Long id) throws CustomException;

    PostOut getById(Long id) throws CustomException;


}
