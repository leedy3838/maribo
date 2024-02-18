package project.maribo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maribo.domain.dto.*;
import project.maribo.domain.entity.Comment;
import project.maribo.domain.entity.Post;
import project.maribo.domain.entity.User;
import project.maribo.domain.entity.type.Category;
import project.maribo.exception.PostNotFoundException;
import project.maribo.exception.UserNotFoundException;
import project.maribo.exception.UserNotMatchException;
import project.maribo.repository.CommentRepository;
import project.maribo.repository.PostRepository;
import project.maribo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createPost(PostCreateRequest postCreateRequest) {

        User user = userRepository.findById(postCreateRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Post post = Post.of(postCreateRequest, user);
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(PostUpdateRequest postUpdateRequest) {

        Post post = postRepository.findById(postUpdateRequest.getPostId())
                .orElseThrow(PostNotFoundException::new);
        Long userId = postUpdateRequest.getUserId();

        validateUser(userId, post);
        log.info("post update 전 : {}", post);
        post.update(postUpdateRequest);
        log.info("post update 후 : {}", post);
    }

    @Transactional
    public void deletePost(Long postId, PostDeleteRequest postDeleteRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        Long userId = postDeleteRequest.getUserId();

        validateUser(userId, post);
        postRepository.deleteById(postId);
    }

    public PostGetResponse getPostById(Long postId) {
        Post post = postRepository.findPostByPostId(postId)
                .orElseThrow(PostNotFoundException::new);

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
                .comments(commentsToResponse(post.getComments()))
                .build();
    }

    private static List<CommentRequest> commentsToResponse(List<Comment> comments) {
        return comments
                .stream()
                .map(comment -> CommentRequest.builder()
                        .userId(comment.getUser().getUserId())
                        .postId(comment.getPost().getPostId())
                        .commentId(comment.getCommentId())
                        .content(comment.getContent())
                        .build())
                .collect(Collectors.toList());
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

    private void validateUser(Long userId, Post post) {
        if (!userId.equals(post.getUser().getUserId())) {
            throw new UserNotMatchException();
        }
    }
}
