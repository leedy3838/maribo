package project.maribo.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.maribo.application.UserService;
import project.maribo.domain.dto.UserRequest;

@Tag(name = "User", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 등록", description = "유저 등록 API")
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok().build();
    }
}
