package org.wolf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wolf.dao.ICollegeDao;
import org.wolf.dto.CollegeDTO;
import org.wolf.entity.College;
import org.wolf.exception.InvalidCollegeException;
import org.wolf.service.ICollegeService;

@Service
public class CollegeServiceImpl implements ICollegeService {

	@Autowired
	ICollegeDao collegeDao;

	@Override
	public College addCollege(CollegeDTO collegeDTO) {
		if (collegeDao.existsById(collegeDTO.getCollegeCode())) {
			throw new InvalidCollegeException();
		} else {
			College college = new College();
			college.setCollegeCode(collegeDTO.getCollegeCode());
			college.setCollegeName(collegeDTO.getCollegeName());
			college.setUserName(collegeDTO.getUserName());
			college.setPassword(collegeDTO.getPassword());
			return collegeDao.save(college);
		}

	}

	@Override
	public College updateCollege(String collegeCode, CollegeDTO collegeDTO) {
		if (collegeDao.existsById(collegeCode)) {
			College college = collegeDao.findById(collegeCode).get();
			college.setCollegeName(collegeDTO.getCollegeName());
			college.setUserName(collegeDTO.getUserName());
			college.setPassword(collegeDTO.getPassword());
			return collegeDao.save(college);
		} else {
			throw new InvalidCollegeException();
		}
	}

	@Override
	public void deleteCollegeById(String collegeCode) {
		if (collegeDao.existsById(collegeCode)) {
			collegeDao.deleteById(collegeCode);
		} else {
			throw new InvalidCollegeException();
		}

	}

	@Override
	public List<CollegeDTO> listAllColleges() {
		return collegeDao.listAll();
	}

}
