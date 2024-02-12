package project.maribo.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.maribo.application.PostService;
import project.maribo.domain.dto.*;
import project.maribo.domain.entity.Post;
import project.maribo.repository.PostRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<Void> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        postService.createPost(postCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/modification")
    public ResponseEntity<Void> updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatePost(postUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestBody PostDeleteRequest postDeleteRequest) {
        postService.deletePost(postId, postDeleteRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getPostById(postId));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getPosts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getAllPosts());
    }
}
