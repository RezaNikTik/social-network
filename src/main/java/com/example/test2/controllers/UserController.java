package com.example.test2.controllers;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.UserIn;
import com.example.test2.model.dtos.UserOut;
import com.example.test2.services.UserService;
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
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserOut>> getAll() {
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserOut> create(@Valid @RequestBody UserIn model, BindingResult bindingResult) {
        return new  ResponseEntity<>(userService.create(model),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") long id) throws CustomException {
        userService.deleteById(id);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<UserOut> getById(@PathVariable(value = "id") long id) throws CustomException {
        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(value = "id") long id, @RequestBody UserIn user) {
        userService.update(id, user);
    }
}
