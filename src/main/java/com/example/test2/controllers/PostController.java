package com.example.test2.controllers;


import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.services.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Validated
@Api(tags = {"PostController"})
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    @ApiOperation(value = "get all posts", response = PostEntity.class, responseContainer = "List")
    public ResponseEntity<List<PostOut>> list(Pageable pageable) {
        return new ResponseEntity<>(postService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "create post", response = PostEntity.class, notes = "create post has validation for time")
    public ResponseEntity<PostOut> create(@Valid @RequestBody PostIn model, BindingResult bindingResult) {
        return new ResponseEntity<>(postService.create(model), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get post by id", response = PostEntity.class)
    public ResponseEntity<PostOut> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete post by id", response = PostEntity.class)
    public void deleteById(@PathVariable("id") Long id) {
        postService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update post by id", response = PostEntity.class)
    public void updateById(@PathVariable Long id, @RequestBody PostIn model) {
        postService.updateById(id, model);
    }


    @PostMapping("/addTagToPost/{postId}/{tagId}")
    @ApiOperation(value = "add tag to post", response = PostEntity.class)
    public void addTagToPost(@PathVariable Long tagId, @PathVariable Long postId) {
        postService.addTagToPost(postId, tagId);
    }

    @GetMapping("/assignTagToPost/{postId}")
    @ApiOperation(value = "get all tag assign to post", response = PostEntity.class, responseContainer = "List")
    public List<TagOut> getAllTagAssignToPost(@PathVariable Long postId) {
        return postService.getAllTagAssignToPost(postId);
    }

    @GetMapping("/getAllPostEntity/{postId}")
    @ApiOperation(value = "get all post with relation", response = PostEntity.class,
            notes = "get all post with relationship with tag and comment by postId", responseContainer = "List")
    public List<PostOut> getAllPostEntityWithRelationsByPostId(@PathVariable Long postId) {
        return postService.getAllPostEntityWithRelationsByPostId(postId);
    }
}
