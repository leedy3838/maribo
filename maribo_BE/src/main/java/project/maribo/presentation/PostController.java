package project.maribo.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.maribo.application.PostService;
import project.maribo.domain.dto.PostCreateRequest;
import project.maribo.domain.dto.PostGetResponse;
import project.maribo.domain.dto.PostUpdateRequest;
import project.maribo.domain.entity.Post;
import project.maribo.repository.PostRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public void createPost(@RequestBody PostCreateRequest postCreateRequest) {
        postService.createPost(postCreateRequest);
    }

    @PostMapping("/modification")
    public void updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatePost(postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getPostById(postId));
    }
}
