package com.example.test2.controllers;


import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.services.PostService;
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
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public ResponseEntity<List<PostOut>> list( Pageable pageable) {
        return new ResponseEntity<>(postService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PostOut> create(@Valid @RequestBody PostIn model, BindingResult bindingResult) {
        return new ResponseEntity<>(postService.create(model), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostOut> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        postService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable Long id, @RequestBody PostIn model) {
        postService.updateById(id, model);
    }


    @PostMapping("/addTagToPost/{postId}/{tagId}")
    public void addTagToPost(@PathVariable Long tagId, @PathVariable Long postId) {
        postService.addTagToPost(postId, tagId);
    }

    @GetMapping("/assignTagToPost/{postId}")
    public List<TagOut> getAllTagAssignToPost(@PathVariable Long postId) {
        return postService.getAllTagAssignToPost(postId);
    }

    @GetMapping("/getAllPostEntity/{postId}")
    public List<PostOut> getAllPostEntityWithRelationsByPostId(@PathVariable Long postId) {
        return postService.getAllPostEntityWithRelationsByPostId(postId);
    }
}
