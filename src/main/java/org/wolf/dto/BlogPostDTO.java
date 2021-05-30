package org.wolf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDTO {
	@NotBlank(message = "Post Title cannot be blank.")
	private @NonNull String postTitle;
	@NotBlank(message = "Post Description cannot be blank.")
	private @NonNull String postDescription;
	private @NonNull Boolean isApproved;
	@NotNull(message = "Author cannot be null.")
	private @NonNull String createdBy;

}
