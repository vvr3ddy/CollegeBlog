package org.wolf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wolf.dto.CollegeDTO;
import org.wolf.entity.College;

@Repository
public interface ICollegeDao extends JpaRepository<College, String> {

	@Query("select new org.wolf.dto.CollegeDTO(c.collegeCode, c.collegeName, c.userName, c.password) from College c order by c.collegeCode")
	List<CollegeDTO> listAll();

	@Query("select new org.wolf.dto.CollegeDTO(c.collegeCode, c.collegeName, c.userName, c.password) from College c where c.userName=:userName order by c.collegeCode")
	CollegeDTO findByUserName(@Param("userName") String userName);
	
	boolean existsByUserName(String userName);

}
