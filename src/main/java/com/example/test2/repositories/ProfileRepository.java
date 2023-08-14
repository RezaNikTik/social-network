package com.example.test2.repositories;

import com.example.test2.model.dtos.UserIn;
import com.example.test2.model.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {


    @Transactional
    @Modifying
    @Query("update profile p set p.city=:#{#user.profileIn.city},p.zipCode=:#{#user.profileIn.zipCode}," +
            "p.country=:#{#user.profileIn.country} " +
            "where p.userEntity.id=:profileId")
    void updateById(@Param("profileId") Long profileId, UserIn user);
}
