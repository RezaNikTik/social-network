package com.example.test2.model.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "tag_post",
                joinColumns = @JoinColumn(name = "tag_id",referencedColumnName ="id"),
                inverseJoinColumns = @JoinColumn(name = "post_id"))
    Set<PostEntity> postEntity;



}
