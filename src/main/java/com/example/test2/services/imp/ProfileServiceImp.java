package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.ProfileIn;
import com.example.test2.model.dtos.ProfileOut;
import com.example.test2.model.entities.Profile;
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
        List<Profile> list=profileRepository.findAll();
        return list.stream().map(ProfileOut::new).toList();

    }

    @Override
    public ProfileOut create(ProfileIn model) {
        Profile profile= model.convertToProfile(new Profile());
        Profile newProfile= profileRepository.save(profile);
        return new ProfileOut(newProfile);
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        Optional<Profile> profile =profileRepository.findById(id);
        if (profile.isEmpty()){
            throw new CustomException("The ID you entered does not exist",1001);
        }
        profileRepository.deleteById(id);
    }

    @Override
    public ProfileOut getById(Long id) throws CustomException {
        Optional<Profile>profile = profileRepository.findById(id);
        if (profile.isEmpty()){
            throw new CustomException("The ID you entered does not exist",1001);
        }
        return new ProfileOut(profile.get());
    }
}
