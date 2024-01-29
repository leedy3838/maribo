package project.maribo.domain.dto;

import lombok.Data;
import project.maribo.domain.entity.Post;
import project.maribo.domain.entity.User;
import project.maribo.domain.entity.type.Category;

@Data
public class PostCreateRequest{
    private Long userId;
    private String title;
    private String content;
    private String photoUrl;
    private String category;
}
