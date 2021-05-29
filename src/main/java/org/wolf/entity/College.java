package org.wolf.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class College {
	@Id
	@Column(unique = true, name = "college_code")
	private String collegeCode;
	private String collegeName;
	@Column(unique = true)
	private String userName;
	private String password;

	@OneToMany(mappedBy = "collegeCode", targetEntity = Student.class, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Student> students;

	@OneToMany(mappedBy = "facultyCode", targetEntity = Faculty.class, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Faculty> faculties;
}
