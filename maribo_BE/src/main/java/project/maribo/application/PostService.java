package project.maribo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.maribo.domain.entity.Post;
import project.maribo.repository.PostRepository;

import java.io.IOException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;

    @Transactional(readOnly = false)
    public String savePost(MultipartFile image, Post post) {

        String objectName = null;
        try {
            objectName = s3Uploader.upload(image, "images");
            post.setPhotoUrl(objectName);
            postRepository.save(post);
        } catch (IOException e) {
            log.info("upload file error");
        }

        return objectName;
    }
}
