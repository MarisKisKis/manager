package task.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.manager.model.dto.ProjectDto;
import task.manager.model.dto.SubprojectDto;
import task.manager.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Valid
@RestController
@RequestMapping("/user/{userId}")
public class UserController {

    private final ProjectService projectService;

    @Autowired
    public UserController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDto> getAllProjects() {
        log.info("Retrieving all projects");
        return projectService.getAllProjects();
    }

    @GetMapping("/subprojects")
    public List<SubprojectDto> getAllSubprojects() {
        log.info("Retrieving all subprojects");
        return projectService.getAllSubprojects();
    }
}
