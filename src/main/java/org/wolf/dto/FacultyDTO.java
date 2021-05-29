package org.wolf.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDTO {
	@NotBlank(message = "Firstname cannot be blank.")
	private @NonNull String firstName;
	private @NonNull String lastName;
	@NotBlank(message = "Username cannot be blank.")
	@Size(min = 6, message = "Username has to be atleast 6 characters long.")
	private @NonNull String userName;
	@NotBlank(message = "Password cannot be blank.")
	@Size(min = 6, max = 32, message = "Password can be atleast 6 characters long and atmost 32 characters long.")
	private @NonNull String password;
	@NotBlank(message = "Faculty code cannot be blank.")
	private @NonNull String facultyCode;
	@NotBlank(message = "College code cannot be empty.")
	private String collegeCode;
	
	
}
