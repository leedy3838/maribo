package project.maribo.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.maribo.application.UserService;
import project.maribo.domain.dto.UserRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 등록", description = "유저 등록 API", tags = "User")
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok().build();
    }
}
