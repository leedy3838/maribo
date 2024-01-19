package project.maribo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.maribo.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
