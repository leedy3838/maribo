package project.maribo.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.maribo.application.CommentService;
import project.maribo.domain.dto.CommentCreateRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public void createComment(
            @PathVariable Long postId,
            @RequestBody CommentCreateRequest commentCreateRequest) {
        commentService.createComment(postId, commentCreateRequest);
    }
}
