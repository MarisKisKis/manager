package task.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
