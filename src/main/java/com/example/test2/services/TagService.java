package com.example.test2.services;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.dtos.TagOut;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    List<TagOut> getAll(Pageable pageable);

    TagOut create(TagIn model);

    void deleteById(Long id) throws CustomException;

    TagOut getById(Long id) throws CustomException;

    void updateById(Long id, TagIn tagIn) throws CustomException;

}
