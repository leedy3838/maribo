package project.maribo.domain.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private Long userId;
    private String content;
}
