package project.maribo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maribo.domain.dto.PostCreateRequest;
import project.maribo.domain.dto.PostUpdateRequest;
import project.maribo.domain.entity.Post;
import project.maribo.domain.entity.User;
import project.maribo.domain.entity.type.Category;
import project.maribo.repository.PostRepository;
import project.maribo.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createPost(PostCreateRequest postCreateRequest) {

        User user = userRepository.findById(postCreateRequest.getUserId())
                .orElseThrow(RuntimeException::new);

        Post post = Post.createPost(postCreateRequest, user);
        postRepository.save(post);
    }

    public void updatePost(PostUpdateRequest postUpdateRequest) {

        Post post = postRepository.findById(postUpdateRequest.getPostId())
                .orElseThrow(RuntimeException::new);

        validateUser(postUpdateRequest, post);

        post.update(postUpdateRequest);
    }

    private static void validateUser(PostUpdateRequest postUpdateRequest, Post post) {
        if (!postUpdateRequest.getUserId().equals(post.getUser().getUserId())) {
            throw new RuntimeException();
        }
    }
}
