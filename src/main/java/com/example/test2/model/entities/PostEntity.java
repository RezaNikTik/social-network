package com.example.test2.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime publishDate;

    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany(mappedBy = "postEntity",fetch = FetchType.LAZY)
    @JsonIgnore
    Set<TagEntity> tagEntity= new HashSet<>();

    @OneToMany(mappedBy = "postEntity",cascade = CascadeType.ALL)
    private Set<CommentEntity> commentEntityPost;


}
