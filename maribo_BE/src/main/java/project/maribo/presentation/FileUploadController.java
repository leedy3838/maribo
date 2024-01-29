package project.maribo.presentation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.maribo.application.PostService;
import project.maribo.domain.entity.Post;

@Slf4j
@RestController
@RequestMapping(value = "/v1/maribo")
@RequiredArgsConstructor
public class FileUploadController {

    private final PostService postService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String savePost(@RequestParam("image") MultipartFile image, Post post) {
        return postService.savePost(image, post);
    }
}