package project.maribo.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.maribo.application.CommentService;
import project.maribo.domain.dto.CommentRequest;
import project.maribo.domain.dto.CommentDeleteRequest;
import project.maribo.domain.dto.CommentRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public void createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest commentRequest) {
        commentService.createComment(postId, commentRequest);
    }

    @PatchMapping("/{commentId}")
    public void updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest commentRequest) {
        commentService.updateComment(commentId, commentRequest);
    }

    @DeleteMapping("{commentId}")
    public void deleteComment(
            @PathVariable Long commentId,
            @RequestBody CommentDeleteRequest commentDeleteRequest) {
        commentService.deleteComment(commentId, commentDeleteRequest);
    }
}
