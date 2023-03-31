package task.manager.service;

import task.manager.model.dto.ProjectDto;
import task.manager.model.dto.SubprojectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto projectDto);

    SubprojectDto createSubproject(SubprojectDto subprojectDto, long projectId);

    ProjectDto updateProject(ProjectDto projectDto, long projectId);

    SubprojectDto updateSubproject(SubprojectDto subprojectDto, long projectId, long subprojectId);

    void deleteProject(long projectId);

    void deleteSubproject(long subprojectId);

    List<ProjectDto> getAllProjects();

    List<SubprojectDto> getAllSubprojects();

}
