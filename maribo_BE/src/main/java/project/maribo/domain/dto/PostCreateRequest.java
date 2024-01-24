package project.maribo.domain.dto;

import project.maribo.domain.entity.User;
import project.maribo.domain.entity.type.Category;

public record PostCreateRequest(
        String title,
        String content,
        Long likeNum,
        String photoUrl,
        String category,
        Long userId
) {
}
