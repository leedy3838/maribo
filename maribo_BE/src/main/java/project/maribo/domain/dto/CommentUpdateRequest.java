package project.maribo.domain.dto;

import lombok.Data;

@Data
public class CommentUpdateRequest {
    private Long userId;
    private String content;
}
