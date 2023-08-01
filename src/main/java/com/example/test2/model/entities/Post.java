package com.example.test2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date publishDate;

//    @ManyToMany(mappedBy = "postSet")
//    Set<Tag> tagSet;
//
//    @OneToMany(mappedBy = "postComment")
//    private Set<Comment>commentPost;


}
