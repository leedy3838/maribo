package project.maribo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.maribo.domain.dto.PostCreateRequest;
import project.maribo.domain.dto.PostUpdateRequest;
import project.maribo.domain.entity.type.Category;
import project.maribo.repository.CommentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "posts")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private Long likeNum;

    @Column(name = "photo_url")
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content, Long likeNum, String photoUrl, Category category, User user) {
        this.title = title;
        this.content = content;
        this.photoUrl = photoUrl;
        this.category = category;
        this.likeNum = likeNum;
        this.user = user;
    }

    public static Post of(PostCreateRequest postCreateRequest, User user) {
        return Post.builder()
                .user(user)
                .category(Category.of(postCreateRequest.getCategory()))
                .title(postCreateRequest.getTitle())
                .content(postCreateRequest.getContent())
                .likeNum(0L)
                .photoUrl(postCreateRequest.getPhotoUrl())
                .build();
    }

    public void update(PostUpdateRequest postUpdateRequest) {
        this.title = postUpdateRequest.getTitle();
        this.content = postUpdateRequest.getContent();
        this.photoUrl = postUpdateRequest.getPhotoUrl();
        this.category = Category.of(postUpdateRequest.getCategory());
    }
}
