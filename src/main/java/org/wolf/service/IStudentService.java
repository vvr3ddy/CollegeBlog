package org.wolf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wolf.dto.ListStudentDTO;
import org.wolf.dto.StudentDTO;
import org.wolf.entity.Student;

@Service
public interface IStudentService {
	public Student addStudent(StudentDTO studentDto);

	public void deleteStudent(String studentUSN);

	public StudentDTO findByUserName(String userName);

	public ListStudentDTO findByUSN(String studentUSN);

	public List<ListStudentDTO> listAllStudents();

	public List<ListStudentDTO> listByCollegeCode(String collegeCode);

	public Student updateStudent(String studentUSN, StudentDTO studentDto);
}
