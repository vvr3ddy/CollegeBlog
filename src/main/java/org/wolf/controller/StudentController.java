package org.wolf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wolf.dto.StudentDTO;
import org.wolf.exception.BlogAppValidationException;
import org.wolf.exception.InvalidFacultyException;
import org.wolf.exception.InvalidStudentException;
import org.wolf.service.IStudentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	IStudentService studentService;

	@PostMapping("/add")
	public ResponseEntity<Object> newStudent(@Valid @RequestBody StudentDTO studentDto, BindingResult bindingResult) {
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
			studentService.addStudent(studentDto);
			return new ResponseEntity<>("Student Created Successfully", HttpStatus.OK);
		} catch (InvalidStudentException e) {
			throw new InvalidStudentException("Student could not be created due to errors.");
		}
	}

	@PutMapping("/update/{studentUSN}")
	public ResponseEntity<Object> updateStudent(@Valid @PathVariable String studentUSN,
			@RequestBody StudentDTO studentDTO, BindingResult bindingResult) {
		if (studentUSN.isBlank() || studentUSN.isEmpty()) {
			throw new InvalidStudentException("StudentUSN is blank.");
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
				studentService.updateStudent(studentUSN, studentDTO);
				return new ResponseEntity<>("Updated Student Details successfully", HttpStatus.OK);
			} catch (InvalidStudentException e) {
				throw new InvalidStudentException("Student could not be updated due to errors.");
			}
		}
	}

	@DeleteMapping("/delete/{studentUSN}")
	public ResponseEntity<Object> deleteStudent(@PathVariable String studentUSN) {
		if (studentUSN.isBlank() || studentUSN.isEmpty()) {
			throw new InvalidStudentException("StudentUSN is blank.");
		} else {
			try {
				studentService.deleteStudent(studentUSN);
				return new ResponseEntity<>("Deleted Student Successfully", HttpStatus.OK);
			} catch (InvalidStudentException e) {
				throw new InvalidStudentException("Student with Specified USN not found");
			}
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<Object> listAllStudents() {
		return new ResponseEntity<>(studentService.listAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/get/college/{clgCode}")
	public ResponseEntity<Object> listAllByClgCode(@PathVariable String collegeCode) {
		if (!(collegeCode.isEmpty() || collegeCode.isBlank())) {
			try {
				return new ResponseEntity<>(studentService.listByCollegeCode(collegeCode), HttpStatus.OK);
			} catch (InvalidStudentException e) {
				throw new InvalidStudentException("Invalid College code specified");
			}
		} else {
			throw new InvalidStudentException("College Code is Blank.");
		}
	}

	@GetMapping("/get/USN/{studentUSN}")
	public ResponseEntity<Object> findByUSN(@PathVariable String studentUSN) {
		if (studentUSN.isBlank() || studentUSN.isEmpty()) {
			throw new InvalidStudentException("Student USN is blank.");
		} else {
			try {
				return new ResponseEntity<>(studentService.findByUSN(studentUSN), HttpStatus.OK);
			} catch (InvalidStudentException e) {
				throw new InvalidStudentException("Student with given USN not found.");
			}
		}
	}

	@GetMapping("/get/userName/{userName}")
	public ResponseEntity<Object> findByUserName(@PathVariable String userName) {
		if (userName.isBlank() || userName.isEmpty()) {
			throw new InvalidStudentException("Username cannot be blank!");
		} else {
			try {
				return new ResponseEntity<>(studentService.findByUserName(userName), HttpStatus.OK);
			} catch (InvalidFacultyException e) {
				throw new InvalidStudentException("Student with given username not found!");
			}
		}
	}
}
