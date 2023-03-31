package task.manager.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import task.manager.model.Project;
import task.manager.model.TaskStatus;
import task.manager.model.TaskType;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Jacksonized
public class TaskDto {

    private Long id;
    private String name;
    private TaskStatus status;
    private TaskType type;
    private LocalDateTime created;
    private LocalDateTime statusChanged;
    private String information;
    private ProjectShortDto project;

    @Getter
    @Setter
    @Builder
    @Jacksonized
    public static class ProjectShortDto {
        private long id;
    }
}
