package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.repositories.PostRepository;
import com.example.test2.services.CommentService;
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
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    @Cacheable(value = "CommentEntity")
    public List<CommentOut> getAll(Pageable pageable) {
        Page<CommentEntity> lists =
                commentRepository.findAll(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort()));
        if (lists.isEmpty()) {
            throw new CustomException("you dont have any data", 1004, HttpStatus.NOT_FOUND);
        }
        return lists.stream().map(CommentOut::new).toList();
    }

    @Override
    @CacheEvict(value = "CommentEntity",allEntries = true)
    public CommentOut create(CommentIn model) {
        CommentEntity commentEntity = model.convertToEntity(new CommentEntity());
        Optional<PostEntity> post = postRepository.findById(model.getPostId());
        if (post.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001, HttpStatus.NOT_FOUND);
        }
        commentEntity.setPostEntity(post.get());
        return new CommentOut(commentRepository.save(commentEntity));
    }

    @Override
    @CacheEvict(value = "CommentEntity",key = "#id")
    public void deleteById(Long id) throws CustomException {
        showMessageForNotValidId(id);
        commentRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "CommentEntity",key = "#id")
    public CommentOut getById(Long id) throws CustomException {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        showMessageForNotValidId(id);
        return new CommentOut(comment.get());
    }

    @Override
    @CacheEvict(value = "CommentEntity",key = "#id")
    public void updateById(Long id, CommentIn model) {
        showMessageForNotValidId(id);
        commentRepository.updateById(id, model);
    }


    private CommentEntity showMessageForNotValidId(Long id) {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001, HttpStatus.NOT_FOUND);
        }
        return comment.get();
    }
}
