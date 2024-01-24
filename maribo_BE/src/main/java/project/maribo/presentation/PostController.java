package project.maribo.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.maribo.application.PostService;
import project.maribo.domain.dto.PostCreateRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public void createPost(@RequestBody PostCreateRequest postCreateRequest) {
        postService.createPost(postCreateRequest);
    }
}
