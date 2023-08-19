package com.example.test2.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime publishDate;

    @Cascade({CascadeType.DETACH})
    @ManyToMany(mappedBy = "postEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<TagEntity> tagEntity = new HashSet<>();

    @Cascade(CascadeType.DETACH)
    @OneToMany(mappedBy = "postEntity")
    private Set<CommentEntity> commentEntityPost;


}
