package task.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
