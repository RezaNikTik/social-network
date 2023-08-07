package com.example.test2.services.imp;


import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.UserIn;
import com.example.test2.model.dtos.UserOut;
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

    private final ProfileServiceImp profileServiceImp;

    public UserServiceImp(UserRepository userRepository, ProfileRepository profileRepository, ProfileServiceImp profileServiceImp) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.profileServiceImp = profileServiceImp;
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
        UserEntity userEntity = model.convertToEntity(new UserEntity());
        ProfileEntity profileEntity = profileServiceImp.create(model.getProfileIn());
        userEntity.setProfileEntity(profileEntity);
        profileEntity.setUserEntity(userEntity);
        userRepository.save(userEntity);
        return new UserOut(userEntity);
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
    public void update(Long userId,UserIn user) {
        showMessageForNotValidId(userId);
        profileRepository.updateById(userId, user);
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
