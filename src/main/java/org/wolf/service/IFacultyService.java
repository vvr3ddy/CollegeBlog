package org.wolf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wolf.dto.FacultyDTO;
import org.wolf.entity.Faculty;

@Service
public interface IFacultyService {

	public Faculty addFaculty(FacultyDTO facultyDTO);

	public Faculty updateFaculty(String facultyCode, FacultyDTO facultyDTO);
	
	public void deleteFaculty(String facultyCode);
	
	public FacultyDTO findByFacultyCode(String facultyCode);

	public List<FacultyDTO> listAllFaculty();

	public List<FacultyDTO> listFacultyByCollegeCode(String collegeCode);

}
