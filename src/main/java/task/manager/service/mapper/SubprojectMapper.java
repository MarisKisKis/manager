package task.manager.service.mapper;

import task.manager.model.Project;
import task.manager.model.Subproject;
import task.manager.model.User;
import task.manager.model.dto.SubprojectDto;

public class SubprojectMapper {

    public static Subproject toSubproject(SubprojectDto subprojectDto, User manager, Project project){
        return Subproject.builder()
                .name(subprojectDto.getName())
                .manager(manager)
                .description(subprojectDto.getDescription())
                .project(project)
                .build();
    }

    public static SubprojectDto toSubprojectDto(Subproject subproject) {
        return SubprojectDto.builder()
                .id(subproject.getId())
                .name(subproject.getName())
                .manager(SubprojectDto.UserShortDto.builder().id(subproject.getManager().getId()).build())
                .description(subproject.getDescription())
                .project(subproject.getProject())
                .build();
    }
}
