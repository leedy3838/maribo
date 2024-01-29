package project.maribo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maribo.domain.dto.PostCreateRequest;
import project.maribo.domain.dto.PostGetResponse;
import project.maribo.domain.dto.PostResponse;
import project.maribo.domain.dto.PostUpdateRequest;
import project.maribo.domain.entity.Post;
import project.maribo.domain.entity.User;
import project.maribo.domain.entity.type.Category;
import project.maribo.repository.PostRepository;
import project.maribo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

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

        Post post = Post.of(postCreateRequest, user);
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(PostUpdateRequest postUpdateRequest) {

        Post post = postRepository.findById(postUpdateRequest.getPostId())
                .orElseThrow(RuntimeException::new);

        validateUser(postUpdateRequest, post);
        log.info("post update 전 : {}", post);
        post.update(postUpdateRequest);
        log.info("post update 후 : {}", post);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public PostGetResponse getPostById(Long postId) {
        Post post = postRepository.findPostByPostId(postId)
                .orElseThrow(RuntimeException::new);

        return PostGetResponse
                .builder()
                .postId(post.getPostId())
                .userId(post.getUser().getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .likeNum(post.getLikeNum())
                .photoUrl(post.getPhotoUrl())
                .category(String.valueOf(post.getCategory()))
                .createdDate(post.getCreatedDate())
                .build();
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostService::postToResponse)
                .collect(Collectors.toList());
    }

    private static PostResponse postToResponse(Post post) {
        return PostResponse
                .builder()
                .postId(post.getPostId())
                .userId(post.getUser().getUserId())
                .title(post.getTitle())
                .createdDate(post.getCreatedDate())
                .build();
    }

    private void validateUser(PostUpdateRequest postUpdateRequest, Post post) {
        if (!postUpdateRequest.getUserId().equals(post.getUser().getUserId())) {
            throw new RuntimeException();
        }
    }
}
