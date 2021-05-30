package org.wolf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wolf.dto.CollegeDTO;
import org.wolf.entity.College;

@Service
public interface ICollegeService {
	public College addCollege(CollegeDTO collegeDTO);

	public void deleteCollegeById(String collegeCode);

	public CollegeDTO findByuserName(String userName);

	public List<CollegeDTO> listAllColleges();
	
	public College updateCollege(String collegeCode, CollegeDTO collegeDTO);
}
