package com.learningdetector.aild.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long studentId; // for updates or responses

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Grade is required")
    private String grade;

    @NotBlank(message = "Section is required")
    private String section;

    @NotBlank(message = "Guardian contact is required")
    private String guardianContact;
}
