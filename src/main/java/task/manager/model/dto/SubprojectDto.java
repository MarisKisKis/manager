package task.manager.model.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.beans.factory.annotation.Autowired;
import task.manager.model.Project;
import task.manager.model.User;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Jacksonized
public class SubprojectDto {
    private Long id;
    private String name;
    private UserShortDto manager;
    private String description;
    private Project project;

    @Getter
    @Setter
    @Builder
    @Jacksonized
    public static class UserShortDto {
        private long id;
    }
}
