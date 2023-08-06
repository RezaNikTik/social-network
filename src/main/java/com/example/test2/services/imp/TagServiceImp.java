package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import com.example.test2.repositories.TagRepository;
import com.example.test2.services.TagService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImp implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImp(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagOut> getAll() {
        List<TagEntity>list = tagRepository.findAll();
        return list.stream().map(TagOut::new).toList();
    }

    @Override
    public TagOut create(TagIn model) {
        TagEntity tagEntity = model.convertToTag(new TagEntity());
        TagEntity newTagEntity = tagRepository.save(tagEntity);
        return new TagOut(newTagEntity);
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        showMessageForNotValidId(id);
        tagRepository.deleteById(id);
    }

    @Override
    public TagOut getById(Long id) throws CustomException {
        Optional<TagEntity> entity = tagRepository.findById(id);
        showMessageForNotValidId(id);
        return new TagOut(entity.get());
    }

    @Override
    public void updateById(Long id, TagIn tagIn) throws CustomException {
        showMessageForNotValidId(id);
         tagRepository.updateById(id, tagIn);
    }


    private TagEntity showMessageForNotValidId(Long id){
        Optional<TagEntity> comment = tagRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001);
        }
        return comment.get();
    }
}
