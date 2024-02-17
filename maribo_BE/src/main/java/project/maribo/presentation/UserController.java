package project.maribo.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.maribo.application.UserService;
import project.maribo.domain.dto.UserRequest;

@Api(tags = "User")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/maribo/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "유저 등록", notes = "유저 등록 API")
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok().build();
    }
}
