package task.manager.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import task.manager.model.User;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Jacksonized
public class ProjectDto {
    private Long id;
    private String name;
    private User manager;
}
