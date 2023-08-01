package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.ProfileIn;
import com.example.test2.model.dtos.ProfileOut;
import com.example.test2.repositories.ProfileRepository;
import com.example.test2.services.ProfileService;

import java.util.List;

public class PostServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;

    public PostServiceImp(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProfileOut> getAll() {
        return null;
    }

    @Override
    public ProfileOut create(ProfileIn model) {
        return null;
    }

    @Override
    public void deleteById(Long id) throws CustomException {

    }

    @Override
    public ProfileOut getById(Long id) throws CustomException {
        return null;
    }
}
