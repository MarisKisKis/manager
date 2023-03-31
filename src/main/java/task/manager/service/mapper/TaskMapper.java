package task.manager.service.mapper;

import task.manager.model.Project;
import task.manager.model.Subproject;
import task.manager.model.Task;
import task.manager.model.dto.TaskDto;

public class TaskMapper {

    public static Task toTaskProject (TaskDto taskDto, Project project) {
        return Task.builder()
                .name(taskDto.getName())
                .status(taskDto.getStatus())
                .type(taskDto.getType())
                .created(taskDto.getCreated())
                .statusChanged(taskDto.getStatusChanged())
                .information(taskDto.getInformation())
                .project(project)
                .build();
    }

    public static Task toTaskSubproject (TaskDto taskDto, Subproject subproject) {
        return Task.builder()
                .name(taskDto.getName())
                .status(taskDto.getStatus())
                .type(taskDto.getType())
                .created(taskDto.getCreated())
                .statusChanged(taskDto.getStatusChanged())
                .information(taskDto.getInformation())
                .subproject(subproject)
                .build();
    }

    public static TaskDto toTaskDto (Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .status(task.getStatus())
                .type(task.getType())
                .created(task.getCreated())
                .statusChanged(task.getStatusChanged())
                .information(task.getInformation())
                .project(TaskDto.ProjectShortDto.builder().id(task.getProject().getId()).build())
                .subproject(TaskDto.SubprojectShortDto.builder().id(task.getSubproject().getId()).build())
                .creator(TaskDto.UserShortDto.builder().id(task.getCreator().getId()).build())
                .build();
    }
}
