package org.wolf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wolf.dao.ICollegeDao;
import org.wolf.dao.IFacultyDao;
import org.wolf.dto.FacultyDTO;
import org.wolf.entity.Faculty;
import org.wolf.exception.InvalidFacultyException;
import org.wolf.service.IFacultyService;

@Service
public class FacultyServiceImpl implements IFacultyService {

	@Autowired
	IFacultyDao facultyDao;

	@Autowired
	ICollegeDao collegeDao;

	@Override
	public Faculty addFaculty(FacultyDTO facultyDTO) {
		if (collegeDao.existsById(facultyDTO.getCollegeCode())) {
			Faculty faculty = new Faculty();

			faculty.setCollegeCode(collegeDao.findById(facultyDTO.getCollegeCode()).get());
			faculty.setFacultyCode(facultyDTO.getFacultyCode());
			faculty.setFirstName(facultyDTO.getFirstName());
			faculty.setLastName(facultyDTO.getLastName());
			faculty.setPassword(facultyDTO.getPassword());
			faculty.setUserName(facultyDTO.getPassword());
			return facultyDao.save(faculty);
		} else {
			throw new InvalidFacultyException();
		}
	}

	@Override
	public Faculty updateFaculty(String facultyCode, FacultyDTO facultyDTO) {
		if (facultyDao.existsById(facultyCode) || !collegeDao.existsById(facultyDTO.getCollegeCode())) {
			Faculty faculty = facultyDao.findById(facultyCode).get();
			faculty.setCollegeCode(collegeDao.findById(facultyDTO.getCollegeCode()).get());
			faculty.setFirstName(facultyDTO.getFirstName());
			faculty.setLastName(facultyDTO.getLastName());
			faculty.setUserName(facultyDTO.getUserName());
			faculty.setPassword(facultyDTO.getPassword());
			return facultyDao.save(faculty);
		} else {
			throw new InvalidFacultyException();
		}
	}

	@Override
	public void deleteFaculty(String facultyCode) {
		if (facultyDao.existsById(facultyCode)) {
			facultyDao.deleteById(facultyCode);
		} else {
			throw new InvalidFacultyException();
		}

	}

	@Override
	public List<FacultyDTO> listAllFaculty() {
		return facultyDao.listAll();
	}

	@Override
	public List<FacultyDTO> listFacultyByCollegeCode(String collegeCode) {
		if (collegeDao.existsById(collegeCode)) {
			return facultyDao.listByCollegeCode(collegeCode);
		} else {
			throw new InvalidFacultyException();
		}
	}

	@Override
	public FacultyDTO findByFacultyCode(String facultyCode) {
		if(facultyDao.existsById(facultyCode)) {
			return facultyDao.findByFacultyCode(facultyCode);
		}else {
			throw new InvalidFacultyException();
		}
	}

}
