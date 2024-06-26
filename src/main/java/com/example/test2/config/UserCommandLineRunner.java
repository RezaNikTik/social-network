package com.example.test2.config;


import com.example.test2.model.dtos.*;
import com.example.test2.services.CommentService;
import com.example.test2.services.PostService;
import com.example.test2.services.TagService;
import com.example.test2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("dev")
public class UserCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserService service;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;


    @Override
    public void run(String... args) throws Exception {
        UserIn bob = new UserIn();
        bob.setFirstName("bob");
        bob.setLastName("boby");
        bob.setPassword("123");
        bob.setAge(25);
        bob.setEmail("bob@gmail.com");
        ProfileIn profileBob = new ProfileIn();
        profileBob.setCity("tehran");
        profileBob.setCountry("iran");
        profileBob.setZipCode("123456");
        bob.setProfileIn(profileBob);

        UserIn tom = new UserIn();
        tom.setFirstName("tom");
        tom.setLastName("tomy");
        tom.setPassword("123");
        tom.setAge(26);
        tom.setEmail("tom@gmail.com");
        ProfileIn profileTom = new ProfileIn();
        profileTom.setCity("WD");
        profileTom.setCountry("usa");
        profileTom.setZipCode("987654321");
        tom.setProfileIn(profileTom);

        UserIn rum = new UserIn();
        rum.setFirstName("rum");
        rum.setLastName("rumy");
        rum.setPassword("123");
        rum.setAge(28);
        rum.setEmail("rum@gmail.com");
        ProfileIn profilerum = new ProfileIn();
        profilerum.setCity("city");
        profilerum.setCountry("Mexico");
        profilerum.setZipCode("456123789");
        rum.setProfileIn(profilerum);

        service.create(bob);
        service.create(tom);
        service.create(rum);

        TagIn tagIn = new TagIn();
        tagIn.setName("electric");


        TagIn tagIn1 = new TagIn();
        tagIn1.setName("day");


        tagService.create(tagIn);
        tagService.create(tagIn1);

        PostIn post = new PostIn();
        post.setTitle("its great day 01");
        post.setPublishDate(LocalDateTime.parse("2024-04-22T20:30:00"));


        PostIn post1 = new PostIn();
        post1.setTitle("its great day 06");
        post1.setPublishDate(LocalDateTime.parse("2024-05-22T20:30:00"));

        PostIn post2 = new PostIn();
        post2.setTitle("its great day 04");
        post2.setPublishDate(LocalDateTime.parse("2024-06-22T20:30:00"));


        PostIn post3 = new PostIn();
        post3.setTitle("its great day 03");
        post3.setPublishDate(LocalDateTime.parse("2024-07-22T20:30:00"));

        PostIn post4 = new PostIn();
        post4.setTitle("its great day 02");
        post4.setPublishDate(LocalDateTime.parse("2024-08-22T20:30:00"));


        PostIn post5 = new PostIn();
        post5.setTitle("its great day 05");
        post5.setPublishDate(LocalDateTime.parse("2024-09-22T20:30:00"));

        postService.create(post);
        postService.create(post1);
        postService.create(post2);
        postService.create(post3);
        postService.create(post4);
        postService.create(post5);

        CommentIn comment = new CommentIn();
        comment.setMessage("joooon");
        comment.setPostId(1L);


        CommentIn comment1 = new CommentIn();
        comment1.setMessage("azizammm");
        comment1.setPostId(1L);

        CommentIn comment2 = new CommentIn();
        comment2.setMessage("azizammm");
        comment2.setPostId(2L);

        commentService.create(comment);
        commentService.create(comment1);
        commentService.create(comment2);
    }
}
