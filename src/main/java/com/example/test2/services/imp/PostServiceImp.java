package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.repositories.PostRepository;
import com.example.test2.services.PostService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository profileRepository;

    private final CommentRepository commentRepository;

    public PostServiceImp(PostRepository profileRepository, CommentRepository commentRepository) {
        this.profileRepository = profileRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public List<PostOut> getAll() {
        List<PostEntity> list = profileRepository.findAll();
        return list.stream().map(PostOut::new).toList();
    }

    @Override
    public PostOut create(PostIn model) {
        PostEntity postEntity = model.convertToPost(new PostEntity());
        PostEntity newPostEntity = profileRepository.save(postEntity);
        return new PostOut(newPostEntity);
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        Optional<PostEntity> post = profileRepository.findById(id);
        if (post.isEmpty()){
            throw  new CustomException("The ID you entered does not exist",1001);
        }
        profileRepository.deleteById(id);
    }

    @Override
    public PostOut getById(Long id) throws CustomException {
        Optional<PostEntity>post =profileRepository.findById(id);
        if (post.isEmpty()){
            throw new CustomException("The ID you entered does not exist",1001);
        }
        return new PostOut(post.get());
    }

    public void updateById(Long id,PostIn model){
        Optional<PostEntity>post = profileRepository.findById(id);
        if (post.isEmpty()){
            throw new CustomException("The ID you entered does not exist",1001);
        }
        profileRepository.updateById(id,model);
    }

    @Override
    public List<CommentOut> getAllCommentByPostId(Long postId) {
//        List<CommentEntity>list = commentRepository.findAll();
//        CommentOut comment= list.stream().map(CommentOut::new).toList();
        return profileRepository.getAllCommentByPostId(postId).stream().map(CommentOut::new).toList();

    }
}
