package com.example.test2.repositories;

import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import com.example.test2.model.entities.Comment;
import com.example.test2.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Transactional
    @Modifying
    @Query("update post p set p.title=:#{#model.title} where p.id=:id")
    void  updateById (@PathVariable("id")Long id, PostIn model);

    @Modifying
    @Query("select c from comment c where c.post.id =:postId")
    List<String> getAllCommentByPostId(@PathVariable Long postId);



}
