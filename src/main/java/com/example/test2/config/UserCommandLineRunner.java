package com.example.test2.config;


import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.dtos.UserIn;
import com.example.test2.services.CommentService;
import com.example.test2.services.PostService;
import com.example.test2.services.TagService;
import com.example.test2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
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
        bob.setCity("tehran");
        bob.setCountry("iran");
        bob.setZipCode("123456789");

        UserIn tom = new UserIn();
        tom.setFirstName("tom");
        tom.setLastName("tomy");
        tom.setPassword("123");
        tom.setAge(26);
        tom.setEmail("tom@gmail.com");
        tom.setCity("WD");
        tom.setCountry("usa");
        tom.setZipCode("987654321");

        UserIn rum = new UserIn();
        rum.setFirstName("rum");
        rum.setLastName("rumy");
        rum.setPassword("123");
        rum.setAge(28);
        rum.setEmail("rum@gmail.com");
        rum.setCity("city");
        rum.setCountry("Mexico");
        rum.setZipCode("456123789");

        service.create(bob);
        service.create(tom);
        service.create(rum);


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

        TagIn tagIn=new TagIn();
        tagIn.setName("electric");


        TagIn tagIn1=new TagIn();
        tagIn1.setName("day");

        tagService.create(tagIn);
        tagService.create(tagIn1);


        PostIn post = new  PostIn();
        post.setTitle("its great day");


        PostIn post1 = new  PostIn();
        post1.setTitle("its great day");

    }
}
