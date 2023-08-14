package com.example.test2.controllers;

import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.services.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("")
    public ResponseEntity<List<TagOut>> getAll(Pageable pageable) {
        return new ResponseEntity<>(tagService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TagOut> create(@RequestBody TagIn model) {
        return new ResponseEntity<>(tagService.create(model), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TagOut> getById(@PathVariable Long id) {
        return new ResponseEntity<>(tagService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        tagService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable Long id, @RequestBody TagIn tagIn) {
        tagService.updateById(id, tagIn);
    }
}
