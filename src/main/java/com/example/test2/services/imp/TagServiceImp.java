package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.repositories.TagRepository;
import com.example.test2.services.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImp implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImp(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagOut> getAll() {
        return null;
    }

    @Override
    public TagOut create(TagIn model) {
        return null;
    }

    @Override
    public void deleteById(Long id) throws CustomException {

    }

    @Override
    public TagOut getById(Long id) throws CustomException {
        return null;
    }
}
