package project.maribo.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.maribo.application.CommentService;
import project.maribo.domain.dto.CommentCreateRequest;
import project.maribo.domain.dto.CommentUpdateRequest;

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

    @PatchMapping("/{commentId}")
    public void updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(commentId, commentUpdateRequest);
    }

    @DeleteMapping("{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
