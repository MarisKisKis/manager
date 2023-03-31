package task.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.manager.model.TaskStatus;
import task.manager.model.dto.ProjectDto;
import task.manager.model.dto.SubprojectDto;
import task.manager.model.dto.TaskDto;
import task.manager.service.ProjectService;
import task.manager.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Valid
@RestController
@RequestMapping("/user/{userId}")
public class UserController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public UserController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/projects")
    public List<ProjectDto> getAllProjects(@PathVariable String userId) {
        log.info("Retrieving all projects");
        return projectService.getAllProjects();
    }

    @GetMapping("/subprojects")
    public List<SubprojectDto> getAllSubprojects(@PathVariable String userId) {
        log.info("Retrieving all subprojects");
        return projectService.getAllSubprojects();
    }

    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks(@PathVariable long userId){
        log.info("Retrieving all tasks");
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public TaskDto createTask(@RequestBody TaskDto taskDto, @PathVariable long userId) {
        log.info("Creating a task");
        return taskService.createTask(taskDto, userId);
    }

    @PatchMapping("/tasks/{taskId}")
    public void changeTaskStatus (@RequestBody TaskStatus taskStatus, @PathVariable long taskId,
                                  @PathVariable long userId) {
        log.info("Changing task status by user");
        taskService.changeTaskStatus(taskStatus, taskId);
    }

    @DeleteMapping("/tasks")
    public void deleteUserTask (@PathVariable long userId, @RequestParam long taskId) {
        log.info("Deleting task created by user");
        taskService.deleteUserTask(userId, taskId);
    }
}
