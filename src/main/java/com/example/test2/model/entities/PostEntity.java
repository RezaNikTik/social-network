package com.example.test2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToMany(mappedBy = "postEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<TagEntity> tagEntity;

    @OneToMany(mappedBy = "postEntity",cascade = CascadeType.ALL)
    private Set<CommentEntity> commentEntityPost;


}
