package project.maribo.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.maribo.application.CommentService;
import project.maribo.domain.dto.CommentRequest;
import project.maribo.domain.dto.CommentDeleteRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest commentRequest) {
        commentService.createComment(postId, commentRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest commentRequest) {
        commentService.updateComment(commentId, commentRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestBody CommentDeleteRequest commentDeleteRequest) {
        commentService.deleteComment(commentId, commentDeleteRequest);
        return ResponseEntity.noContent().build();
    }
}
