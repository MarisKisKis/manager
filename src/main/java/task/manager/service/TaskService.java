package task.manager.service;

import task.manager.model.TaskStatus;
import task.manager.model.dto.TaskDto;

import java.util.List;

public interface TaskService {
    void deleteTask(long taskId);

    TaskDto updateTask(TaskDto taskDto, long taskId);

    List<TaskDto> getAllTasks();

    TaskDto createTask(TaskDto taskDto, long userId);

    void changeTaskStatus(TaskStatus taskStatus, long taskId);

    void deleteUserTask(long userId, long taskId);
}
