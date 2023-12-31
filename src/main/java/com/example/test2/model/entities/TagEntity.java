package com.example.test2.model.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;


    @Cascade(value = CascadeType.DETACH)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tag_post",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    Set<PostEntity> postEntity = new HashSet<>();


}
