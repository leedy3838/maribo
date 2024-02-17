package project.maribo.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.maribo.application.CommentService;
import project.maribo.domain.dto.CommentRequest;
import project.maribo.domain.dto.CommentDeleteRequest;

@Tag(name = "Comment", description = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 등록", description = "댓글 등록 API")
    @PostMapping("/{postId}")
    public ResponseEntity<Void> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest commentRequest) {
        commentService.createComment(postId, commentRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "댓글 수정", description = "댓글 수정 API")
    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest commentRequest) {
        commentService.updateComment(commentId, commentRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제 API")
    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestBody CommentDeleteRequest commentDeleteRequest) {
        commentService.deleteComment(commentId, commentDeleteRequest);
        return ResponseEntity.noContent().build();
    }
}
