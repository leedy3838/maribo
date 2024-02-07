package project.maribo.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequest {
    private Long userId;
    private Long postId;
    private Long commentId;
    private String content;
}
