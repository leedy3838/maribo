package project.maribo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maribo.domain.dto.PostCreateRequest;
import project.maribo.domain.entity.Post;
import project.maribo.domain.entity.User;
import project.maribo.domain.entity.type.Category;
import project.maribo.repository.PostRepository;
import project.maribo.repository.UserRepository;

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

        Category category = Category.of(postCreateRequest.getCategory());
        Post post = Post.createPost(postCreateRequest, category, user);
        postRepository.save(post);
    }
}
