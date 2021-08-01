package org.wolf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wolf.dto.FacultyDTO;
import org.wolf.dto.ListFacultyDTO;
import org.wolf.entity.Faculty;

@Service
public interface IFacultyService {

	public Faculty addFaculty(FacultyDTO facultyDTO);

	public void deleteFaculty(String facultyCode);
	
	public FacultyDTO findByFacultyCode(String facultyCode);
	
	public FacultyDTO findByUserName(String userName);
	
	public List<ListFacultyDTO> listAllFaculty();

	public List<ListFacultyDTO> listFacultyByCollegeCode(String collegeCode);

	public Faculty updateFaculty(String facultyCode, FacultyDTO facultyDTO);

}
