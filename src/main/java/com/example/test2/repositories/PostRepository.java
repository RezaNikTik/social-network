package com.example.test2.repositories;

import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {



    @Transactional
    @Modifying
    @Query("update post p set p.title=:#{#model.title} where p.id=:id")
    void  updateById (@PathVariable("id")Long id, PostIn model);

    @Modifying
    @Query("select c from comment c where c.postEntity.id =:postId")
    List<CommentEntity> getAllCommentByPostId(@PathVariable Long postId);


    @Transactional
    @Modifying
    @Query(value = "insert into TAG_POST(tag_id, post_id) values(:tagId, :postId)", nativeQuery = true)
     void addTagToPost(@Param("tagId") Long tagId, @Param("postId") Long postId);

}
