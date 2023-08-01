package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.services.CommentService;

import java.util.List;

public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentOut> getAll() {
        return null;
    }

    @Override
    public CommentOut create(CommentIn model) {
        return null;
    }

    @Override
    public void deleteById(Long id) throws CustomException {

    }

    @Override
    public CommentOut getById(Long id) throws CustomException {
        return null;
    }
}
