package org.wolf.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeDTO {
	@NotBlank(message = "College code cannot be empty.")
	private @NonNull String collegeCode;
	@NotBlank(message = "College Name cannot be blank.")
	private @NonNull String collegeName;
	@NotBlank(message = "Username cannot be blank.")
	private @NonNull String userName;
	@NotBlank(message = "Password cannot be empty.")
	@Size(max = 32, min = 6, message = "password must be atleast 6 characters and atmost 32 characters long")
	private @NonNull String password;
}
