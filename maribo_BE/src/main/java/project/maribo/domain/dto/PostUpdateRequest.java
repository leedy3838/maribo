package project.maribo.domain.dto;

import lombok.Data;

@Data
public class PostUpdateRequest {
    private Long userId;
    private Long postId;
    private String title;
    private String content;
    private String photoUrl;
    private String category;
}
