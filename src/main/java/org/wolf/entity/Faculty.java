package org.wolf.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Faculty implements Serializable {

	private static final long serialVersionUID = -5893260738629391782L;

	@Id
	@Column(unique = true, name = "faculty_code")
	private String facultyCode;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String userName;
	private String password;

	@ManyToOne(targetEntity = College.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "clg_code")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private College collegeCode;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "student_reg_id")
	private List<Student> studentList;

}
