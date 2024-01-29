package project.maribo.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import project.maribo.domain.dto.CommentCreateRequest;
import project.maribo.domain.dto.PostCreateRequest;
import project.maribo.domain.entity.type.Category;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @NotBlank
    @Column(name = "content")
    private String content;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public static Comment of(CommentCreateRequest commentCreateRequest, User user, Post post) {
        return Comment.builder()
                .user(user)
                .post(post)
                .content(commentCreateRequest.getContent())
                .build();
    }
}
