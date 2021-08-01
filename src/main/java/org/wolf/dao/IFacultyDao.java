package org.wolf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wolf.dto.FacultyDTO;
import org.wolf.dto.ListFacultyDTO;
import org.wolf.entity.Faculty;

@Repository
public interface IFacultyDao extends JpaRepository<Faculty, String> {

	boolean existsByUserName(String userName);

	@Query("select new "
			+ "org.wolf.dto.FacultyDTO(f.firstName, f.lastName, f.userName, f.password, f.facultyCode, f.collegeCode.collegeCode) "
			+ "from Faculty f where f.facultyCode like :facCode " + "order by f.facultyCode")
	FacultyDTO findByFacultyCode(@Param("facCode") String facultyCode);

	@Query("select new "
			+ "org.wolf.dto.FacultyDTO(f.firstName, f.lastName, f.userName, f.password, f.facultyCode, f.collegeCode.collegeCode) "
			+ "from Faculty f where f.userName like :userName order by f.facultyCode")
	FacultyDTO findByUserName(@Param("userName") String userName);

	@Query("select new "
			+ "org.wolf.dto.ListFacultyDTO(CONCAT(f.firstName, ' ', f.lastName), f.facultyCode, f.userName, f.collegeCode.collegeCode) "
			+ "from Faculty f order by f.facultyCode")
	List<ListFacultyDTO> listAll();

	@Query("select new "
			+ "org.wolf.dto.ListFacultyDTO(CONCAT(f.firstName, ' ', f.lastName), f.facultyCode, f.userName, f.collegeCode.collegeCode) "
			+ "from Faculty f where f.collegeCode.collegeCode like :collegeCode order by f.collegeCode.collegeCode")
	List<ListFacultyDTO> listByCollegeCode(String collegeCode);

}
