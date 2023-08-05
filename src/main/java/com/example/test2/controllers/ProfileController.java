package com.example.test2.controllers;

import com.example.test2.model.dtos.ProfileIn;
import com.example.test2.model.dtos.ProfileOut;
import com.example.test2.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@Validated
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProfileOut>> getAll(){
        return new ResponseEntity<>(profileService.getAll(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProfileOut> create(@Valid @RequestBody ProfileIn model, BindingResult bindingResult){
        return new ResponseEntity<>(profileService.create(model),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id")Long id){
        profileService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileOut> getById(@PathVariable("id")Long id ){
        return new ResponseEntity<>(profileService.getById(id),HttpStatus.OK);
    }
}
