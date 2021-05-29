package org.wolf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wolf.dao.ICollegeDao;
import org.wolf.dao.IStudentDao;
import org.wolf.dto.StudentDTO;
import org.wolf.entity.Student;
import org.wolf.exception.InvalidStudentException;
import org.wolf.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentDao studentDao;

	@Autowired
	ICollegeDao collegeDao;

	@Override
	public Student addStudent(StudentDTO studentDto) {
		if (studentDao.existsById(studentDto.getRegistrationNumber())
				|| (!collegeDao.existsById(studentDto.getCollegeCode()))) {
			throw new InvalidStudentException();
		} else {
			Student student = new Student();

			student.setFirstName(studentDto.getFirstName());
			student.setLastName(studentDto.getLastName());
			student.setCollegeCode(collegeDao.findById(studentDto.getCollegeCode()).get());
			student.setRegistrationNumber(studentDto.getRegistrationNumber());
			student.setUserName(studentDto.getUserName());
			student.setPassword(studentDto.getPassword());

			return studentDao.save(student);
		}

	}

	@Override
	public Student updateStudent(String studentUSN, StudentDTO studentDto) {
		if (studentDao.existsById(studentUSN)) {
			Student student = studentDao.findById(studentUSN).get();
			student.setFirstName(studentDto.getFirstName());
			student.setLastName(studentDto.getLastName());
			student.setCollegeCode(collegeDao.findById(studentDto.getCollegeCode()).get());
			student.setUserName(studentDto.getUserName());
			student.setPassword(studentDto.getPassword());

			return studentDao.save(student);
		} else {
			throw new InvalidStudentException();
		}
	}

	@Override
	public void deleteStudent(String studentUSN) {
		if (studentDao.existsById(studentUSN)) {
			studentDao.deleteById(studentUSN);
		} else {
			throw new InvalidStudentException();
		}

	}

	@Override
	public List<StudentDTO> listAllStudents() {
		return studentDao.listAll();
	}

	@Override
	public List<StudentDTO> listByCollegeCode(String collegeCode) {
		return studentDao.listAllByCollegeCode(collegeCode);
	}

	@Override
	public StudentDTO findByUSN(String studentUSN) {
		if (studentDao.existsById(studentUSN)) {
			return studentDao.findByUSN(studentUSN);
		} else
			throw new InvalidStudentException();
	}

}
