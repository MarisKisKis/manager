package task.manager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.manager.exception.ObjectNotFoundException;
import task.manager.model.Project;
import task.manager.model.Subproject;
import task.manager.model.Task;
import task.manager.model.TaskStatus;
import task.manager.model.dto.TaskDto;
import task.manager.repository.ProjectRepository;
import task.manager.repository.SubprojectRepository;
import task.manager.repository.TaskRepository;
import task.manager.service.mapper.TaskMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final SubprojectRepository subprojectRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, SubprojectRepository subprojectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.subprojectRepository = subprojectRepository;
    }

    @Override
    public void deleteTask(long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, long taskId) {
        if(taskRepository.findById(taskId).isEmpty()) {
            throw new ObjectNotFoundException("Task doesn't exist");
        }
        if(projectRepository.findById(taskDto.getProject().getId()).isEmpty()) {
            throw new ObjectNotFoundException("Project with this task doesn't exist");
        }
        if (subprojectRepository.findById(taskDto.getSubproject().getId()).isEmpty()){
            throw new ObjectNotFoundException("Subproject with this task doesn't exist");
        }
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Task task = taskOptional.get();
        if (taskDto.getName()!=null){
            task.setName(taskDto.getName());
        }
        if (taskDto.getStatus()!=null){
            task.setStatus(taskDto.getStatus());
        }
        if (taskDto.getType()!=null) {
            task.setType(taskDto.getType());
        }
        if (taskDto.getStatusChanged()!=null) {
            task.setStatusChanged(taskDto.getStatusChanged());
        }
        if (taskDto.getInformation()!=null) {
            task.setInformation(taskDto.getInformation());
        }
        if (taskDto.getProject()!=null) {
            Optional<Project> projectOptional = projectRepository.findById(taskDto.getProject().getId());
            Project project = projectOptional.get();
            task.setProject(project);
        }
        if (taskDto.getSubproject()!=null) {
            Optional<Subproject> subprojectOptional = subprojectRepository.findById(taskDto.getSubproject().getId());
            Subproject subproject = subprojectOptional.get();
            task.setSubproject(subproject);
        }
        return taskDto;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto, long userId) {
        taskDto.setCreated(LocalDateTime.now());
        taskDto.setCreator(TaskDto.UserShortDto.builder().id(userId).build());
        if (taskDto.getProject()!=null) {
            if (projectRepository.findById(taskDto.getProject().getId()).isEmpty()) {
                throw new ObjectNotFoundException("Designated project doesn't exist");
            }
            Optional<Project> projectOptional = projectRepository.findById(taskDto.getProject().getId());
            Project project = projectOptional.get();
            taskRepository.save(TaskMapper.toTaskProject(taskDto, project));

        } else if (taskDto.getSubproject()!=null) {
            if (subprojectRepository.findById(taskDto.getSubproject().getId()).isEmpty()) {
                throw new ObjectNotFoundException("Designated subproject doesn't exist");
            }
            Optional<Subproject> subprojectOpt = subprojectRepository.findById(taskDto.getSubproject().getId());
            Subproject subproject = subprojectOpt.get();
            taskRepository.save(TaskMapper.toTaskSubproject(taskDto, subproject));
        }

        return taskDto;
    }

    @Override
    public void changeTaskStatus(TaskStatus taskStatus, long taskId) {
        if (taskRepository.findById(taskId).isEmpty()) {
            throw new ObjectNotFoundException("Task doesn't exist");
        }
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Task task = taskOptional.get();
        task.setStatus(taskStatus);
        task.setStatusChanged(LocalDateTime.now());
    }

    @Override
    public void deleteUserTask(long userId, long taskId) {
        taskRepository.deleteTaskByCreatorIsAndId(userId, taskId);
    }
}
