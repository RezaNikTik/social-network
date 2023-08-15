package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostIn {
    private String title;
    @FutureOrPresent(message = "The time you entered is less than the current time")
    private LocalDateTime publishDate;

    public PostEntity convertToEntity(PostEntity postEntity) {
        if (postEntity == null) {
            postEntity = new PostEntity();
        }
        postEntity.setTitle(this.title);
        postEntity.setPublishDate(this.publishDate);
        return postEntity;
    }


}
