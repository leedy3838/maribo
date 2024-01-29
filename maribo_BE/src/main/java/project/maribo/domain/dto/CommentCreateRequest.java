package project.maribo.domain.dto;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private Long userId;
    private String content;
}
