package com.example.test2.controllers;

import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import com.example.test2.services.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@Api(tags = {"TagController"})
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("")
    @ApiOperation(value = "get all tags", response = TagEntity.class, responseContainer = "List")
    public ResponseEntity<List<TagOut>> getAll(Pageable pageable) {
        return new ResponseEntity<>(tagService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "create tag", response = TagEntity.class)
    public ResponseEntity<TagOut> create(@RequestBody TagIn model) {
        return new ResponseEntity<>(tagService.create(model), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get tag by id", response = TagEntity.class)
    public ResponseEntity<TagOut> getById(@PathVariable Long id) {
        return new ResponseEntity<>(tagService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete tag by id", response = TagEntity.class)
    public void deleteById(@PathVariable Long id) {
        tagService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update tag by id", response = TagEntity.class)
    public void updateById(@PathVariable Long id, @RequestBody TagIn tagIn) {
        tagService.updateById(id, tagIn);
    }
}
