package org.wolf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wolf.dto.FacultyDTO;
import org.wolf.entity.Faculty;

@Repository
public interface IFacultyDao extends JpaRepository<Faculty, String> {

	@Query("select new "
			+ "org.wolf.dto.FacultyDTO(f.firstName, f.lastName, f.userName, f.password, f.facultyCode, f.collegeCode.collegeCode) "
			+ "from Faculty f order by f.facultyCode")
	List<FacultyDTO> listAll();

	@Query("select new "
			+ "org.wolf.dto.FacultyDTO(f.firstName, f.lastName, f.userName, f.password, f.facultyCode, f.collegeCode.collegeCode) "
			+ "from Faculty f where f.collegeCode.collegeCode = :collegeCode order by f.collegeCode.collegeCode")
	List<FacultyDTO> listByCollegeCode(String collegeCode);

	@Query("select new "
			+ "org.wolf.dto.FacultyDTO(f.firstName, f.lastName, f.userName, f.password, f.facultyCode, f.collegeCode.collegeCode) "
			+ "from Faculty f where f.facultyCode=:facCode "
			+ "order by f.facultyCode")
	
	FacultyDTO findByFacultyCode(@Param("facCode") String facultyCode);

}
