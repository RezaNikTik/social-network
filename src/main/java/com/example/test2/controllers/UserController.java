package com.example.test2.controllers;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.UserIn;
import com.example.test2.model.dtos.UserOut;
import com.example.test2.model.entities.TagEntity;
import com.example.test2.model.entities.UserEntity;
import com.example.test2.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
@Api(tags = {"UserController"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ApiOperation(value = "get all users and profiles", response = UserEntity.class, responseContainer = "List")
    public ResponseEntity<List<UserOut>> getAll(Pageable pageable) {
        return new ResponseEntity<>(userService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "create user and profile with together", response = UserEntity.class,
            notes = "user have validation for email")
    public ResponseEntity<UserOut> create(@Valid @RequestBody UserIn model, BindingResult bindingResult) {
        return new ResponseEntity<>(userService.create(model), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete user and profile with together with id", response = UserEntity.class)
    public void deleteById(@PathVariable(value = "id") long id) throws CustomException {
        userService.deleteById(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get user and profile with together with id", response = UserEntity.class)
    public ResponseEntity<UserOut> getById(@PathVariable(value = "id") long id) throws CustomException {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "update user and profile with together with id", response = UserEntity.class)
    public void update(@PathVariable(value = "userId") long userId, @RequestBody UserIn user) {
        userService.update(userId, user);
    }
}
