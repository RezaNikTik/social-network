package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.repositories.PostRepository;
import com.example.test2.repositories.TagRepository;
import com.example.test2.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final TagRepository tagRepository;

    public PostServiceImp(PostRepository profileRepository, CommentRepository commentRepository, TagRepository tagRepository) {
        this.postRepository = profileRepository;
        this.commentRepository = commentRepository;
        this.tagRepository = tagRepository;
    }


    @Override
    public List<PostOut> getAll() {
        List<PostEntity> list = postRepository.findAll();
        if (list.isEmpty()) {
            throw new CustomException("you dont have any data", 1004, HttpStatus.NOT_FOUND);
        }
        return list.stream().map(PostOut::new).toList();
    }

    @Override
    public PostOut create(PostIn model) {
        PostEntity postEntity = model.convertToPost(new PostEntity());
        if (model.getPublishDate().isEqual(LocalDateTime.now())) {
            throw new CustomException("The time you entered is the same as the present time", 1003, HttpStatus.BAD_REQUEST);
        }
        if (model.getPublishDate().isBefore(LocalDateTime.now())) {
            throw new CustomException("The time you entered is less than the current time", 1003, HttpStatus.BAD_REQUEST);
        }
        PostEntity newPostEntity = postRepository.save(postEntity);
        return new PostOut(newPostEntity);
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        showMessageForNotValidId(id);
        postRepository.deleteById(id);
    }

    @Override
    public PostOut getById(Long id) throws CustomException {
        Optional<PostEntity> post = postRepository.findById(id);
        showMessageForNotValidId(id);
        return new PostOut(post.get());
    }

    public void updateById(Long id, PostIn model) {
        showMessageForNotValidId(id);
        postRepository.updateById(id, model);
    }

    @Override
    public List<CommentOut> getAllCommentByPostId(Long postId) {
//        List<CommentEntity>list = commentRepository.findAll();
//        CommentOut comment= list.stream().map(CommentOut::new).toList();
        return postRepository.getAllCommentByPostId(postId).stream().map(CommentOut::new).toList();
    }

    public void addTagToPost(@PathVariable Long postId, @PathVariable Long tagId) {
        Optional<TagEntity> tagEntity = tagRepository.findById(tagId);
        if (tagEntity.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001, HttpStatus.NOT_FOUND);
        }
        showMessageForNotValidId(postId);
        postRepository.addTagToPost(tagId, postId);
    }

    @Override
    public List<TagOut> getAllTagAssignToPost(Long postId) {
        showMessageForNotValidId(postId);
        return postRepository.getAllTagAssignToPost(postId).stream().map(TagOut::new).toList();

    }


    private PostEntity showMessageForNotValidId(Long id) {
        Optional<PostEntity> comment = postRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001, HttpStatus.NOT_FOUND);
        }
        return comment.get();
    }

}
