package task.manager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.manager.exception.ObjectNotFoundException;
import task.manager.model.Project;
import task.manager.model.Subproject;
import task.manager.model.User;
import task.manager.model.dto.ProjectDto;
import task.manager.model.dto.SubprojectDto;
import task.manager.repository.ProjectRepository;
import task.manager.repository.SubprojectRepository;
import task.manager.repository.UserRepository;
import task.manager.service.mapper.ProjectMapper;
import task.manager.service.mapper.SubprojectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final SubprojectRepository subprojectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, SubprojectRepository subprojectRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.subprojectRepository = subprojectRepository;
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        projectRepository.save(ProjectMapper.toProject(projectDto));
        return projectDto;
    }

    @Override
    public SubprojectDto createSubproject(SubprojectDto subprojectDto, long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Project project = projectOptional.get();
        Optional<User> userOptional = userRepository.findById(subprojectDto.getProject().getId());
        User user = userOptional.get();
        subprojectRepository.save(SubprojectMapper.toSubproject(subprojectDto, user, project));
        return subprojectDto;
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto, long projectId) {
        if (projectRepository.findById(projectId).isEmpty()) {
            throw new ObjectNotFoundException("Project doesn't exist");
        }
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Project project = projectOptional.get();
        if(projectDto.getName() != null) {
            project.setName(projectDto.getName());
        }
        if(projectDto.getManager() != null) {
            project.setManager(projectDto.getManager());
        }
        return projectDto;
    }

    @Override
    public SubprojectDto updateSubproject(SubprojectDto subprojectDto, long projectId, long subprojectId) {
        if(subprojectRepository.findById(subprojectId).isEmpty()) {
            throw new ObjectNotFoundException("Subproject doesn't exist");
        }
        Optional<Subproject> subprojectOptional = subprojectRepository.findById(subprojectId);
        Subproject subproject = subprojectOptional.get();
        Optional<User> userOptional = userRepository.findById(subprojectDto.getManager().getId());
        User manager = userOptional.get();
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Project project = projectOptional.get();
        if (subprojectDto.getName() != null) {
            subproject.setName(subprojectDto.getName());
        }
        if (subprojectDto.getManager() != null) {
            subproject.setManager(manager);
        }
        if (subprojectDto.getDescription() != null) {
            subproject.setDescription(subprojectDto.getDescription());
        }
        if (subprojectDto.getProject() != null) {
            subproject.setProject(project);
        }
        return subprojectDto;
    }

    @Override
    public void deleteProject(long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public void deleteSubproject(long subprojectId) {
        subprojectRepository.deleteById(subprojectId);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::toProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubprojectDto> getAllSubprojects() {
        return subprojectRepository.findAll()
                .stream()
                .map(SubprojectMapper::toSubprojectDto)
                .collect(Collectors.toList());
    }
}
