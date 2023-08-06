package com.example.test2.services.imp;


import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.UserIn;
import com.example.test2.model.dtos.UserOut;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.ProfileEntity;
import com.example.test2.model.entities.UserEntity;
import com.example.test2.repositories.ProfileRepository;
import com.example.test2.repositories.UserRepository;
import com.example.test2.services.UserService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    public UserServiceImp(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<UserOut> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(UserOut::new).toList();
    }

    @Override
    public UserOut create(UserIn model) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        model.setPassword(passwordEncoder.encode(model.getPassword()));
//        Optional<ProfileEntity> profileEntity = profileRepository.findById(model.getProfileId());
//        if (profileEntity == null){
//            throw new CustomException("your profileEntity dose not exist",1001);
//        }
        ProfileEntity profileEntity = model.convertToEntity(new ProfileEntity());
        profileRepository.save(profileEntity);
        UserEntity userEntity = model.convertToEntity(new UserEntity());
        userEntity.setProfileEntity(profileEntity);
        UserEntity newUser = userRepository.save(userEntity);
//        newUser.setProfileEntity(profileEntity);
//        userEntity.setProfileEntity(profileEntity.get());
        return new UserOut(newUser);
    }

    @Override
    public void deleteById(Long id) throws CustomException {
        getUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserOut getById(Long id) throws CustomException {
        UserEntity user = getUserById(id);
        return new UserOut(user);
    }

    private UserEntity getUserById(Long id) throws CustomException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        showMessageForNotValidId(id);
        return optionalUser.get();
    }

    @Override
    public void update(Long userId, Long profileId, UserIn user) {
        profileRepository.updateById(profileId, user);
        userRepository.update(userId, user);

    }


    private UserEntity showMessageForNotValidId(Long id){
        Optional<UserEntity> comment = userRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CustomException("The ID you entered does not exist", 1001);
        }
        return comment.get();
    }
}
