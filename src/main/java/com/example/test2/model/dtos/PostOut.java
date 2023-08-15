package com.example.test2.model.dtos;

import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostOut  implements Serializable {
    private Long id;
    private String title;
    private LocalDateTime publishDate;
    private List<CommentOut> comments;
    private List<TagOut> tags;

    public PostOut(PostEntity postEntity) {
        if (postEntity != null) {
            this.id = postEntity.getId();
            this.title = postEntity.getTitle();
            this.publishDate = postEntity.getPublishDate();

            if (Hibernate.isInitialized(postEntity.getCommentEntityPost()) && postEntity.getCommentEntityPost() != null) {
                comments = postEntity.getCommentEntityPost().stream().map(CommentOut::new).collect(Collectors.toList());
            }

            if (Hibernate.isInitialized(postEntity.getTagEntity()) && postEntity.getTagEntity() != null) {
                tags = postEntity.getTagEntity().stream().map(TagOut::new).collect(Collectors.toList());
            }
        }
    }
}
