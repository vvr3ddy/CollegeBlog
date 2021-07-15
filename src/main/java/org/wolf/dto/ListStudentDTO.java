package org.wolf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListStudentDTO {
    private String fullName;
    private String userName;
    private String registrationNumber;
    private String collegeCode;
}
