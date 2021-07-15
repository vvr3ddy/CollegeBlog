package org.wolf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListFacultyDTO {
    private String fullName;
    private String facultyCode;
    private String userName;
    private String collegeCode;
}
