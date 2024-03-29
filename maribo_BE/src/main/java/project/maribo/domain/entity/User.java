package project.maribo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.maribo.domain.dto.UserRequest;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Email
    @Column(name = "email")
    private String email;

    @Builder
    public User(String email) {
        this.email = email;
    }

    public static User of(UserRequest userRequest) {
        return User
                .builder()
                .email(userRequest.getEmail())
                .build();
    }
}
