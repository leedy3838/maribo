package project.maribo.domain.dto;

import lombok.Builder;
import lombok.Data;
import project.maribo.domain.entity.Comment;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PostGetResponse {
    private Long userId;
    private Long postId;
    private Long likeNum;
    private String title;
    private String content;
    private String photoUrl;
    private String category;
    private LocalDate createdDate;
    private List<Comment> comments;
}
