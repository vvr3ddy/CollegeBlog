package org.wolf.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NonNull;

@Data
public class BlogPostDTO {
	@NotBlank(message = "Post Title cannot be blank.")
	private @NonNull String postTitle;
	@NotBlank(message = "Post Description cannot be blank.")
	private @NonNull String postDescription;
	private @NonNull Boolean isApproved;
	@NotNull(message = "Author cannot be null.")
	private @NonNull String createdBy;

}
