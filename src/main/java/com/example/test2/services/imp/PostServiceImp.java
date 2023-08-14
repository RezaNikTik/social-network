package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import com.example.test2.repositories.PostRepository;
import com.example.test2.repositories.TagRepository;
import com.example.test2.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public PostServiceImp(PostRepository profileRepository, TagRepository tagRepository) {
        this.postRepository = profileRepository;
        this.tagRepository = tagRepository;
    }


    @Override
    public List<PostOut> getAll(Integer size, Integer pageCount) {
        Pageable pageable = PageRequest.of(size, pageCount);
        Page<PostEntity> postOutPages = postRepository.findAll(pageable);
        if (postOutPages.isEmpty()) {
            throw new CustomException("you dont have any data", 1004, HttpStatus.NOT_FOUND);
        }
        return postOutPages.stream().map(PostOut::new).toList();
    }

    @Override
    public PostOut create(PostIn model) {
        PostEntity postEntity = model.convertToEntity(new PostEntity());
        PostEntity newPostEntity = postRepository.save(postEntity);
        return new PostOut(newPostEntity);
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        Optional<PostEntity> optionalPost = postRepository.findById(id);
        showMessageForNotValidId(optionalPost);
        postRepository.deleteById(id);
    }

    @Override
    public PostOut getById(Long id) throws CustomException {
        PostEntity postEntity = showMessageForNotValidId(id);
        return new PostOut(postEntity);
    }

    public void updateById(Long id, PostIn model) {
        showMessageForNotValidId(id);
        postRepository.updateById(id, model);
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

    @Override
    public List<PostOut> getAllPostEntityWithRelationsByPostId(Long postId) {
        Optional<PostEntity> postOut = postRepository.findById(postId);
        if (postOut.isEmpty()) {
            throw new CustomException("you dont have any data", 1004, HttpStatus.NOT_FOUND);
        }
        return postRepository.getAllPostEntityWithRelationsByPostId(postId).stream().map(PostOut::new).toList();
    }


    private PostEntity showMessageForNotValidId(Long id) {
        Optional<PostEntity> optionalPost = postRepository.findById(id);
        return showMessageForNotValidId(optionalPost);
    }

    private PostEntity showMessageForNotValidId(Optional<PostEntity> optionalPost) {
        if (optionalPost.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001, HttpStatus.NOT_FOUND);
        }
        return optionalPost.get();
    }

}
