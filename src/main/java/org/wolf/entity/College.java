package org.wolf.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class College {
	@Id
	@Column(name = "clg_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_generator")
	@SequenceGenerator(name = "college_generator", sequenceName = "college_seq", allocationSize = 1)
	private @NonNull Long id;
	private @NonNull String firstName;
	private @NonNull String lastName;
	@Column(unique = true)
	private @NonNull String userName;
	private @NonNull String password;
	@Column(unique = true)
	private @NonNull String collegeCode;

	@OneToMany(mappedBy = "collegeCode", targetEntity = Student.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private @NonNull List<Student> students;
	
	@OneToMany(mappedBy = "facultyCode", targetEntity = Faculty.class, cascade= { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private @NonNull List<Faculty> faculties;
}
