package com.example.test2.controllers;

import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/{size}/{pageCount}")
    public ResponseEntity<List<CommentOut>> list(@PathVariable Integer size, @PathVariable Integer pageCount) {
        return new ResponseEntity<>(commentService.getAll(size, pageCount), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommentOut> create(@RequestBody CommentIn model, BindingResult bindingResult) {
        return new ResponseEntity<>(commentService.create(model), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CommentOut> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(commentService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable Long id, @RequestBody CommentIn model) {
        commentService.updateById(id, model);
    }


}
