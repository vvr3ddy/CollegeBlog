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
import org.springframework.web.bind.annotation.RestController;
import org.wolf.dto.FacultyDTO;
import org.wolf.exception.BlogAppValidationException;
import org.wolf.exception.InvalidCollegeException;
import org.wolf.exception.InvalidFacultyException;
import org.wolf.service.IFacultyService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	IFacultyService facultyService;

	@DeleteMapping("/delete/{facultyCode}")
	public ResponseEntity<Object> deleteFaculty(@PathVariable String facultyCode) {
		if (facultyCode.isBlank() || facultyCode.isEmpty()) {
			throw new InvalidFacultyException("Faculty Code is empty!");
		} else {
			try {
				facultyService.deleteFaculty(facultyCode);
				return new ResponseEntity<>("Deleted Faculty from Records Successfully!", HttpStatus.OK);
			} catch (InvalidFacultyException e) {
				throw new InvalidFacultyException("Faculty with given code not found!");
			}
		}
	}

	@GetMapping("/get/id/{facultyCode}")
	public ResponseEntity<Object> findByFacultyCode(@PathVariable String facultyCode){
		if(facultyCode.isBlank()||facultyCode.isEmpty()) {
			throw new InvalidFacultyException("Faculty Code is blank!");
		}else {
			try {
				return new ResponseEntity<>(facultyService.findByFacultyCode(facultyCode), HttpStatus.OK);
			} catch(InvalidFacultyException e) {
				throw new InvalidFacultyException("Faculty with given code not found!");
			}
		}
	}

	@GetMapping("/get/userName/{userName}")
	public ResponseEntity<Object> findByUserName(@PathVariable String userName) {
		if(userName.isBlank()||userName.isEmpty()) {
			throw new InvalidFacultyException("Username cannot be blank!");
		}else {
			try {
				return new ResponseEntity<>(facultyService.findByUserName(userName), HttpStatus.OK);
			}catch(InvalidFacultyException e) {
				throw new InvalidFacultyException("Faculty with given username not found!");
			}
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<Object> listAllFaculty() {
		return new ResponseEntity<>(facultyService.listAllFaculty(), HttpStatus.OK);
	}
	
	@GetMapping("/get/college/{collegeCode}")
	public ResponseEntity<Object> listFacultyByCollege(@PathVariable String collegeCode) {
		if(collegeCode.isBlank()||collegeCode.isEmpty()) {
			throw new InvalidFacultyException("College code is blank!");
		}else {
			try {
				return new ResponseEntity<>(facultyService.listFacultyByCollegeCode(collegeCode), HttpStatus.OK);
			}catch(InvalidFacultyException e) {
				throw new InvalidFacultyException("College with given code not found!");
			}
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> newFaculty(@Valid @RequestBody FacultyDTO facultyDTO, BindingResult bindingResult) {
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
			facultyService.addFaculty(facultyDTO);
			return new ResponseEntity<>("Faculty added successfully!", HttpStatus.OK);
		} catch (InvalidFacultyException e) {
			throw new InvalidFacultyException("Faculty could not be created due to errors!");
		}
	}
	
	@PutMapping("/update/{facultyCode}")
	public ResponseEntity<Object> updateFaculty(@PathVariable String facultyCode,
			@Valid @RequestBody FacultyDTO facultyDTO, BindingResult bindingResult) {
		if (facultyCode.isBlank() || facultyCode.isEmpty()) {
			throw new InvalidCollegeException("Faculty code is blank!");
		} else if (bindingResult.hasErrors()) {
			System.out.println("Some errors exist!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			List<String> errMessages = new ArrayList<>();
			for (FieldError fe : fieldErrors) {
				errMessages.add(fe.getDefaultMessage());
			}
			throw new BlogAppValidationException(errMessages);
		}

		else {
			try {
				facultyService.updateFaculty(facultyCode, facultyDTO);
				return new ResponseEntity<>("Updated Faculty Details Successfully!", HttpStatus.OK);
			} catch (InvalidFacultyException e) {
				throw new InvalidFacultyException("Faculty with given code not found!");
			}
		}

	}
}
