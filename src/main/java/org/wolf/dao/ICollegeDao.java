package org.wolf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wolf.dto.CollegeDTO;
import org.wolf.entity.College;

@Repository
public interface ICollegeDao extends JpaRepository<College, String> {

	@Query("select new org.wolf.dto.CollegeDTO(c.collegeCode, c.collegeName, c.userName, c.password) from College c")
	List<CollegeDTO> listAll();

}
