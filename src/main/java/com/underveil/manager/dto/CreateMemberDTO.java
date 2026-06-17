package com.underveil.manager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateMemberDTO {

    @NotBlank
    private String name;

    @NotNull
    @Min(14)
    @Max(100)
    private Integer age;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String position;

    @NotBlank
    private String status;

    @NotBlank
    private String currentProject;
}