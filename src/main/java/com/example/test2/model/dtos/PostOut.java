package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostOut {

    private String title;
    private LocalDateTime publishDate;

    public PostOut(PostEntity postEntity) {
        this.title = postEntity.getTitle();
        this.publishDate = postEntity.getPublishDate();
    }
}
