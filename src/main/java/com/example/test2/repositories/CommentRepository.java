package com.example.test2.repositories;

import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Transactional
    @Modifying
    @Query("update comment  c set c.message=:#{#model.message} where c.id=:id")
    void updateById(@PathVariable Long id, CommentIn model);
}
