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

    @Transactional
    public String savePost(MultipartFile image, Post post) {

        String objectName = null;
        try {
            objectName = s3Uploader.upload(image, "images");
            //아래 부분 dto로 전환하면서 고치기
            post.setPhotoUrl(objectName);
            post.setPhotoName(image.getName());

            postRepository.save(post);
        } catch (IOException e) {
            log.info("upload file error");
        }

        return objectName;
    }
}
