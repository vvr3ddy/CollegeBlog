package org.wolf.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListBlogPostDTO {
    private Long postId;
    private String postTitle;
    private String postDescription;
    private String authorId;
    private String authorName;
    private LocalDateTime creationDate;
    private LocalDateTime updateTime;
    private Integer flaggedCount;
}
