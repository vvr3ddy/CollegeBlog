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
import org.wolf.dto.CollegeDTO;
import org.wolf.exception.BlogAppValidationException;
import org.wolf.exception.InvalidCollegeException;
import org.wolf.service.ICollegeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/college")
public class CollegeController {

	@Autowired
	ICollegeService collegeService;

	@DeleteMapping("/delete/{collegeCode}")
	public ResponseEntity<Object> deleteCollegeById(@PathVariable String collegeCode){
		if(collegeCode.isBlank()||collegeCode.isEmpty()) {
			throw new InvalidCollegeException("College code is blank!");
		}else {
			try {
				collegeService.deleteCollegeById(collegeCode);
				return new ResponseEntity<>("Deleted College from Records!", HttpStatus.OK);
			}catch(InvalidCollegeException e) {
				throw new InvalidCollegeException("College with given code not found!");
			}
		}
	}

	@GetMapping("/get/userName/{userName}")
	public ResponseEntity<Object> findByUserName(@PathVariable String userName){
		if(userName.isBlank()|| userName.isEmpty()) {
			throw new InvalidCollegeException("Username cannot be blank!");
		}
		else {
			try {
				return new ResponseEntity<>(collegeService.findByuserName(userName), HttpStatus.OK);
			}catch(InvalidCollegeException e) {
				throw new InvalidCollegeException("College with given username doesn't exist!");
			}
		}
	}

	@GetMapping("/get")
	public ResponseEntity<Object> listAllColleges() {
		return new ResponseEntity<>(collegeService.listAllColleges(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> newCollege(@Valid @RequestBody CollegeDTO collegeDto, BindingResult bindingResult) {
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
			collegeService.addCollege(collegeDto);
			return new ResponseEntity<>("College added successfully!", HttpStatus.OK);
		} catch (InvalidCollegeException e) {
			throw new InvalidCollegeException("College could not be created due to errors!");
		}
	}
	
	@PutMapping("/update/{collegeCode}")
	public ResponseEntity<Object> updateCollege(@PathVariable String collegeCode,
			@Valid @RequestBody CollegeDTO collegeDTO, BindingResult bindingResult) {
		if (collegeCode.isBlank() || collegeCode.isEmpty()) {
			throw new InvalidCollegeException("College code is blank!");
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
				collegeService.updateCollege(collegeCode, collegeDTO);
				return new ResponseEntity<>("Updated College Details Successfully!", HttpStatus.OK);
			} catch (InvalidCollegeException e) {
				throw new InvalidCollegeException("College with given code not found!");
			}
		}

	}

}
