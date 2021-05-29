package org.wolf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	@NotBlank(message = "Firstname cannot be blank.")
	private @NonNull String firstName;
	private @NonNull String lastName;
	@NotBlank(message = "Username cannot be blank.")
	private @NonNull String userName;
	@NotBlank(message = "Password cannot be blank.")
	private @NonNull String password;
	@NotBlank(message = "Registration Number must not be blank.")
	private @NonNull String registrationNumber;
	@NotBlank(message = "College code cannot be empty.")
	private @NonNull String collegeCode;
}
