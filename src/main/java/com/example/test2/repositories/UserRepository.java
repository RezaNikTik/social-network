package com.example.test2.repositories;


import com.example.test2.model.dtos.UserIn;
import com.example.test2.model.dtos.UserOut;
import com.example.test2.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {


    @Transactional
    @Modifying
    @Query("update UserEntity ue set ue.firstName=:#{#user.firstName},ue.lastName=:#{#user.lastName},ue.email=:#{#user.email}," +
            "ue.age=:#{#user.age} where ue.id=:id")
    void update(@Param("id") Long id,UserIn user);

}
