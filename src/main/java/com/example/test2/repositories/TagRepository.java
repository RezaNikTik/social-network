package com.example.test2.repositories;

import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity,Long> {

    @Transactional
    @Modifying
    @Query("update tag t set t.name=:#{#tagIn.name} where t.id=:id")
    void updateById(@PathVariable Long id, TagIn tagIn);
}
