package com.employee_management_system.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProgrammerDTO {

    @NotNull
    @Size(max = 255)
    private String programmingLanguages;

    private Integer id;

}
