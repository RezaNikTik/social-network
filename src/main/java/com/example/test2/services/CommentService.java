package com.example.test2.services;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.dtos.CommentOut;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {


    List<CommentOut> getAll(Pageable pageable);

    CommentOut create(CommentIn model);

    void deleteById(Long id) throws CustomException;

    CommentOut getById(Long id) throws CustomException;

    void updateById(Long id, CommentIn model);
}
