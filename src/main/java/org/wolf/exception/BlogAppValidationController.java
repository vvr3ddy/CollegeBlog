package org.wolf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class BlogAppValidationController {
	
	@ExceptionHandler(value = InvalidCollegeException.class)
	public ResponseEntity<Object> handleException(InvalidCollegeException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = InvalidStudentException.class)
	public ResponseEntity<Object> handleException(InvalidStudentException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = InvalidFacultyException.class)
	public ResponseEntity<Object> handleException(InvalidFacultyException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = InvalidBlogPostException.class)
	public ResponseEntity<Object> handleException(InvalidBlogPostException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = BlogAppValidationException.class)
	public ResponseEntity<Object> handleException(BlogAppValidationException exception) {
		return new ResponseEntity<>(exception.getMessages(), HttpStatus.BAD_REQUEST);
	}
}
