package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.model.entities.TagEntity;
import com.example.test2.repositories.TagRepository;
import com.example.test2.services.TagService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImp implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImp(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Cacheable(value = "TagEntity")
    public List<TagOut> getAll(Pageable pageable) {
        Page<TagEntity> list = tagRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
        if (list.isEmpty()) {
            throw new CustomException("you dont have any data", 1004, HttpStatus.NOT_FOUND);
        }
        return list.stream().map(TagOut::new).toList();
    }

    @Override
    @CacheEvict(value = "TagEntity",allEntries = true)
    public TagOut create(TagIn model) {
        TagEntity tagEntity = model.convertToEntity(new TagEntity());
        TagEntity newTagEntity = tagRepository.save(tagEntity);
        return new TagOut(newTagEntity);
    }

    @Override
    @CacheEvict(value = "TagEntity", key = "#id")
    public void deleteById(Long id) throws CustomException {
        showMessageForNotValidId(id);
        tagRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "TagEntity", key = "#id")
    public TagOut getById(Long id) throws CustomException {
        Optional<TagEntity> entity = tagRepository.findById(id);
        showMessageForNotValidId(id);
        return new TagOut(entity.get());
    }

    @Override
    @CacheEvict(value = "TagEntity",allEntries = true)
    public void updateById(Long id, TagIn tagIn) throws CustomException {
        showMessageForNotValidId(id);
        tagRepository.updateById(id, tagIn);
    }


    private TagEntity showMessageForNotValidId(Long id) {
        Optional<TagEntity> comment = tagRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001, HttpStatus.NOT_FOUND);
        }
        return comment.get();
    }
}
