package task.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.manager.model.dto.ProjectDto;
import task.manager.model.dto.SubprojectDto;
import task.manager.model.dto.TaskDto;
import task.manager.service.ProjectService;
import task.manager.service.TaskService;
import task.manager.service.UserService;

import javax.validation.Valid;

@Slf4j
@Valid
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public AdminController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/users")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/users/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @PostMapping("/projects")
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        log.info("Creating a project by admin");
        return projectService.createProject(projectDto);
    }

    @PostMapping("/projects/{projectId}/subprojects")
    public SubprojectDto createSubroject(@RequestBody SubprojectDto subprojectDto, @PathVariable long projectId){
        log.info("Creating a subproject by admin");
        return projectService.createSubproject(subprojectDto, projectId);
    }

    @PatchMapping("/projects")
    public ProjectDto updateProject(@RequestBody ProjectDto projectDto, @PathVariable long projectId) {
        log.info("Updating a project by admin");
        return projectService.updateProject(projectDto, projectId);
    }

    @PatchMapping("/projects/{projectId}/subprojects/{subprojectId}")
    public SubprojectDto updateSubproject(@RequestBody SubprojectDto subprojectDto, @PathVariable long projectId,
                                          @RequestParam long subprojectId) {
        log.info("Updating a subproject by admin");
        return projectService.updateSubproject(subprojectDto, projectId, subprojectId);
    }

    @PatchMapping("/tasks/{taskId}")
    public TaskDto updateTask(@RequestBody TaskDto taskDto, @PathVariable long taskId) {
        log.info("Updating a task by admin");
        return taskService.updateTask(taskDto, taskId);
    }

    @DeleteMapping("/projects")
    public void deleteProject(@RequestParam long projectId) {
        log.info("Deleting a project with id{}", projectId);
        projectService.deleteProject(projectId);
    }

    @DeleteMapping("/projects/{projectId}/subprojects/{subprojectId}")
    public void deleteSubproject(@PathVariable long projectId, @RequestParam long subprojectId) {
        log.info("Deleting a subproject with id{}", subprojectId);
        projectService.deleteSubproject(subprojectId);
    }

    @DeleteMapping("/tasks")
    public void deleteTask(@RequestParam long taskId) {
        log.info("Deleting task with id{}", taskId);
        taskService.deleteTask(taskId);
    }
}
