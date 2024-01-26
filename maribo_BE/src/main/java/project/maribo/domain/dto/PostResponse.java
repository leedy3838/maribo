package project.maribo.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PostResponse {
    private Long userId;
    private Long postId;
    private String title;
    private LocalDate createdDate;
}
