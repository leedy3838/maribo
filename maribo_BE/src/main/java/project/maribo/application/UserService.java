package project.maribo.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.maribo.domain.dto.UserRequest;
import project.maribo.domain.entity.User;
import project.maribo.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserRequest userRequest) {

        String email = userRequest.getEmail();
        User user = new User(email);

        userRepository.save(user);
    }
}
