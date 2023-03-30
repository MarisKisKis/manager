package task.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.model.Subproject;

public interface SubprojectRepository extends JpaRepository<Subproject, Long> {
}
