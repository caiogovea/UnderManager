package com.underveil.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponseDTO {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String position;
    private String status;
    private String currentProject;
}