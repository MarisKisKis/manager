package task.manager.service.mapper;

import task.manager.model.Project;
import task.manager.model.dto.ProjectDto;

public class ProjectMapper {

    public static Project toProject(ProjectDto projectDto) {
        return Project.builder()
                .name(projectDto.getName())
                .manager(projectDto.getManager())
                .build();
    }

    public static ProjectDto toProjectDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .manager(project.getManager())
                .build();
    }
}
