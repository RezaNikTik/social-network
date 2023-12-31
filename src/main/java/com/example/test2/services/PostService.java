package com.example.test2.services;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import com.example.test2.model.dtos.TagOut;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PostService {

    List<PostOut> getAll(Pageable pageable);

    PostOut create(PostIn model);

    void deleteById(Long id) throws CustomException;

    PostOut getById(Long id) throws CustomException;

     void updateById(Long id, PostIn model);

    void addTagToPost(@PathVariable Long postId, @PathVariable Long tagId);

    List<TagOut> getAllTagAssignToPost(@PathVariable Long postId);

    List<PostOut> getAllPostEntityWithRelationsByPostId(Long postId);


}
