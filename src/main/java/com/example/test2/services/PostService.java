package com.example.test2.services;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PostService {

    List<PostOut> getAll();

    PostOut create(PostIn model);

    void deleteById(Long id) throws CustomException;

    PostOut getById(Long id) throws CustomException;

    public void updateById(Long id,PostIn model);

    List<CommentOut> getAllCommentByPostId(Long postId);

    public void addTagToPost(@PathVariable Long tagId, @PathVariable Long postId);


}
