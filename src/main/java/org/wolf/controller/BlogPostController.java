package org.wolf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.wolf.dto.BlogPostDTO;
import org.wolf.exception.BlogAppValidationException;
import org.wolf.exception.InvalidBlogPostException;
import org.wolf.service.IBlogPostService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/posts")
public class BlogPostController {

	@Autowired
	IBlogPostService blogPostService;

	@GetMapping("/approve/{postId}")
	public ResponseEntity<Object> approvePost(@PathVariable Long postId) {
		if (postId != null) {
			try {
				blogPostService.approvePost(postId);
				return new ResponseEntity<>("Post has been approved Succesfully!", HttpStatus.OK);
			} catch (InvalidBlogPostException e) {
				throw new InvalidBlogPostException("Post with given Id not found!");
			}
		} else {
			throw new InvalidBlogPostException("Post Id cannot be blank!");
		}
	}

	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<Object> deletePost(@PathVariable Long postId) {
		if (postId == null) {
			throw new InvalidBlogPostException("Post Id cannot be blank!");
		} else {
			try {
				blogPostService.deleteBlogPost(postId);
				return new ResponseEntity<>("Post Deleted Successfully!", HttpStatus.OK);
			} catch (InvalidBlogPostException e) {
				throw new InvalidBlogPostException("Post with given id not found!");
			}
		}
	}

	@GetMapping("/get/")
	public ResponseEntity<Object> findAllPosts() {
		return new ResponseEntity<>(blogPostService.listAllPosts(), HttpStatus.OK);
	}

	@GetMapping("/get/findByAuthor/{userName}")
	public ResponseEntity<Object> findByAuthors(@PathVariable String userName) {
		if (userName.isBlank() || userName.isEmpty()) {
			throw new InvalidBlogPostException("Author username cannot be blank!");
		} else {
			try {
				return new ResponseEntity<>(blogPostService.findPostsByAuthor(userName), HttpStatus.OK);
			} catch (InvalidBlogPostException e) {
				throw new InvalidBlogPostException("Author with given username not found!");
			}
		}
	}

	@GetMapping("/get/flaggedPosts")
	public ResponseEntity<Object> findFlaggedPosts() {
		return new ResponseEntity<>(blogPostService.listFlaggedPosts(), HttpStatus.OK);
	}

	@PutMapping("/flagPost/{postId}")
	public ResponseEntity<Object> flagPost(@PathVariable Long postId, @RequestParam String facultyCode) {
		if (postId == null && (facultyCode.isBlank() || facultyCode.isEmpty())) {
			throw new InvalidBlogPostException("Post Id or Faculty Coe is blank!");
		} else {
			try {
				blogPostService.flagPost(postId, facultyCode);
				return new ResponseEntity<>("Post Flagged Successfully!", HttpStatus.OK);
			} catch (InvalidBlogPostException e) {
				throw new InvalidBlogPostException(
						"Post could not be flagged either because post with Id doesn't exist or because faculty with code doesn't exist!");
			}
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Object> newPost(@Valid @RequestBody BlogPostDTO blogPostDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some errors exist!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			List<String> errMessages = new ArrayList<>();
			for (FieldError fe : fieldErrors) {
				errMessages.add(fe.getDefaultMessage());
			}
			throw new BlogAppValidationException(errMessages);
		}
		try {
			blogPostService.addPost(blogPostDto);
			return new ResponseEntity<>("New Post created Successfully!", HttpStatus.OK);
		} catch (InvalidBlogPostException e) {
			throw new InvalidBlogPostException("Post could not be created due to some errors!");
		}
	}

	@PutMapping("/update/{postId}")
	public ResponseEntity<Object> updatePost(@PathVariable Long postId, @Valid @RequestBody BlogPostDTO blogPostDto,
			BindingResult bindingResult) {
		if (postId == null) {
			throw new InvalidBlogPostException("Post Id cannot be blank!");
		} else {
			if (bindingResult.hasErrors()) {
				System.out.println("Some errors exist!");
				List<FieldError> fieldErrors = bindingResult.getFieldErrors();

				List<String> errMessages = new ArrayList<>();
				for (FieldError fe : fieldErrors) {
					errMessages.add(fe.getDefaultMessage());
				}
				throw new BlogAppValidationException(errMessages);
			}

			try {
				blogPostService.updatePost(postId, blogPostDto);
				return new ResponseEntity<>("Post Updated Successfully!", HttpStatus.OK);
			} catch (InvalidBlogPostException e) {
				throw new InvalidBlogPostException("Post with given id not found!");
			}
		}
	}
}
