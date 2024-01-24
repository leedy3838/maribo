package project.maribo.domain.dto;

import lombok.Data;
import project.maribo.domain.entity.Post;
import project.maribo.domain.entity.User;
import project.maribo.domain.entity.type.Category;

@Data
public class PostCreateRequest{
    Long userId;
    String title;
    String content;
    String photoUrl;
    String category;
}
