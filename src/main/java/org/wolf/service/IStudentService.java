package org.wolf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wolf.dto.StudentDTO;
import org.wolf.entity.Student;

@Service
public interface IStudentService {
	public Student addStudent(StudentDTO studentDto);

	public Student updateStudent(String studentUSN, StudentDTO studentDto);

	public void deleteStudent(String studentUSN);

	public List<StudentDTO> listAllStudents();

	public List<StudentDTO> listByCollegeCode(String collegeCode);
	
	public StudentDTO findByUSN(String studentUSN);
	
	public StudentDTO findByUserName(String userName);
}
