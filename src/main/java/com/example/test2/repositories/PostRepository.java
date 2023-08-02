package com.example.test2.repositories;

import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

//    @Transactional
//    @Modifying
//    @Query()
//      addTag(@PathVariable Long tagId,@PathVariable Long postId ){
//        EntityManager entityManger=new EntityManager()
//        entityManger.createNamedQuery("INSERT INTO tag_post VALUES (?,?)").
//                setParameter(1,tagId).setParameter(2,postId);
//    }

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tag_post VALUES (tagId,postId)",nativeQuery = true)
    void addTagAndPost(@PathVariable Long tagId,@PathVariable Long postId );
}
