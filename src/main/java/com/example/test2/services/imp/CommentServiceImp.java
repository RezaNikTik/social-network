package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.entities.Comment;
import com.example.test2.model.entities.Post;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.repositories.PostRepository;
import com.example.test2.services.CommentService;
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
        List<Comment> list = commentRepository.findAll();
        return list.stream().map(CommentOut::new).toList();
    }

    @Override
    public CommentOut create(CommentIn model) {
        Comment comment = model.convertToComment(new Comment());
        Optional<Post> post = postRepository.findById(model.getPostId());
        if (post.isEmpty()){
            throw new CustomException("The ID you entered does not exist", 1001);
        }
        comment.setPost(post.get());
        Comment newComment = commentRepository.save(comment);
        return new CommentOut(newComment);
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001);
        }
        commentRepository.deleteById(id);
    }

    @Override
    public CommentOut getById(Long id) throws CustomException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001);
        }
        return new CommentOut(comment.get());
    }

    @Override
    public void updateById(Long id, CommentIn model) {
        commentRepository.updateById(id, model);
    }
}
