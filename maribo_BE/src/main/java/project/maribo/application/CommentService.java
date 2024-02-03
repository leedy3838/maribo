package project.maribo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maribo.domain.dto.CommentCreateRequest;
import project.maribo.domain.dto.CommentDeleteRequest;
import project.maribo.domain.dto.CommentUpdateRequest;
import project.maribo.domain.dto.PostCreateRequest;
import project.maribo.domain.entity.Comment;
import project.maribo.domain.entity.Post;
import project.maribo.domain.entity.User;
import project.maribo.repository.CommentRepository;
import project.maribo.repository.PostRepository;
import project.maribo.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public void createComment(Long postId, CommentCreateRequest commentCreateRequest) {

        User user = userRepository.findById(commentCreateRequest.getUserId())
                .orElseThrow(RuntimeException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);

        Comment comment = Comment.of(commentCreateRequest, user, post);
        commentRepository.save(comment);
    }

    public void updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(RuntimeException::new);
        Long userId = commentUpdateRequest.getUserId();

        validateUser(userId, comment);
        comment.updateComment(commentUpdateRequest);
    }

    public void deleteComment(Long commentId, CommentDeleteRequest commentDeleteRequest) {
        Comment comment = commentRepository.findById(commentId)
                        .orElseThrow(RuntimeException::new);
        Long userId = commentDeleteRequest.getUserId();

        validateUser(userId, comment);

        commentRepository.deleteById(commentId);
    }

    private static void validateUser(Long userId, Comment comment) {
        if (!userId.equals(comment.getUser().getUserId())) {
            throw new RuntimeException();
        }
    }
}
