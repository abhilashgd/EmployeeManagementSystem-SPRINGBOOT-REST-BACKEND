package com.employee_management_system.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeDTO {

    private Long facebookid;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    private Integer phoneNumber;

    @Size(max = 255)
    private String experience;

    @NotNull
    @Size(max = 255)
    private String programmer;

    private Integer manager;

    @Size(max = 255)
    private String doctor;

}
