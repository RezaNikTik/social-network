package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.repositories.PostRepository;
import com.example.test2.services.CommentService;
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
    public List<CommentOut> getAll() {
        List<CommentEntity> list = commentRepository.findAll();
        if (list.isEmpty()){
            throw new CustomException("you dont have any data",1004,HttpStatus.NOT_FOUND);
        }
        return list.stream().map(CommentOut::new).toList();
    }

    @Override
    public CommentOut create(CommentIn model) {
        CommentEntity commentEntity = model.convertToComment(new CommentEntity());
        Optional<PostEntity> post = postRepository.findById(model.getPostId());
        if (post.isEmpty()){
            throw new CustomException("The ID you entered does not exist", 1001, HttpStatus.NOT_FOUND);
        }
        commentEntity.setPostEntity(post.get());
        return new CommentOut(commentRepository.save(commentEntity));
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        showMessageForNotValidId(id);
        commentRepository.deleteById(id);
    }

    @Override
    public CommentOut getById(Long id) throws CustomException {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        showMessageForNotValidId(id);
        return new CommentOut(comment.get());
    }

    @Override
    public void updateById(Long id, CommentIn model) {
        commentRepository.updateById(id, model);
    }


    private CommentEntity showMessageForNotValidId(Long id){
        Optional<CommentEntity> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001, HttpStatus.NOT_FOUND);
        }
        return comment.get();
    }
}
