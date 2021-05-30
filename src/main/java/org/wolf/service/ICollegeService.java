package org.wolf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wolf.dto.CollegeDTO;
import org.wolf.entity.College;

@Service
public interface ICollegeService {
	public College addCollege(CollegeDTO collegeDTO);

	public College updateCollege(String collegeCode, CollegeDTO collegeDTO);

	public void deleteCollegeById(String collegeCode);

	public List<CollegeDTO> listAllColleges();
	
	public CollegeDTO findByuserName(String userName);
}
