package task.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
