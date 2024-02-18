package project.maribo.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.maribo.application.PostService;
import project.maribo.domain.dto.*;

import java.util.List;

@Tag(name = "Post", description = "게시물 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/post")
public class PostController {

    private final PostService postService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 등록 성공", content = @Content(schema = @Schema(implementation = PostCreateRequest.class)))})
    @Operation(summary = "게시물 등록", description = "게시물 등록 API")
    @PostMapping("/post")
    public ResponseEntity<Void> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        postService.createPost(postCreateRequest);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 수정 성공", content = @Content(schema = @Schema(implementation = PostUpdateRequest.class)))})
    @Operation(summary = "게시물 수정", description = "게시물 수정 API")
    @PostMapping("/modification")
    public ResponseEntity<Void> updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatePost(postUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 삭제 성공", content = @Content(schema = @Schema(implementation = PostDeleteRequest.class)))})
    @Operation(summary = "게시물 삭제", description = "게시물 삭제 API")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestBody PostDeleteRequest postDeleteRequest) {
        postService.deletePost(postId, postDeleteRequest);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 조회 성공", content = @Content(schema = @Schema(implementation = PostGetResponse.class)))})
    @Operation(summary = "게시물 조회", description = "게시물 조회 API")
    @GetMapping("/{postId}")
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getPostById(postId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 리스트 조회 성공", content = @Content(schema = @Schema(implementation = PostResponse.class)))})
    @Operation(summary = "게시물 리스트 조회", description = "게시물 리스트 조회 API")
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getPosts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getAllPosts());
    }
}
