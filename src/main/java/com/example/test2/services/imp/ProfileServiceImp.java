package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.ProfileIn;
import com.example.test2.model.dtos.ProfileOut;
import com.example.test2.model.entities.ProfileEntity;
import com.example.test2.repositories.ProfileRepository;
import com.example.test2.services.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImp(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProfileOut> getAll() {
        List<ProfileEntity> list=profileRepository.findAll();
        return list.stream().map(ProfileOut::new).toList();

    }

    @Override
    public ProfileEntity create(ProfileIn model) {
        ProfileEntity profileEntity = model.convertToProfile(new ProfileEntity());
//        ProfileEntity newProfileEntity = profileRepository.save(profileEntity);
        return profileEntity;
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        showMessageForNotValidId(id);
        profileRepository.deleteById(id);
    }

    @Override
    public ProfileOut getById(Long id) throws CustomException {
        Optional<ProfileEntity>profile = profileRepository.findById(id);
        showMessageForNotValidId(id);
        return new ProfileOut(profile.get());
    }

    private ProfileEntity showMessageForNotValidId(Long id){
        Optional<ProfileEntity> comment = profileRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001);
        }
        return comment.get();
    }
}
