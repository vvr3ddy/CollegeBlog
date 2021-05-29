package org.wolf.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Faculty {
	@Id
	@Column(name = "fac_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faculty_generator")
	@SequenceGenerator(name = "faculty_generator", sequenceName = "faculty_seq", allocationSize = 1)
	private @NonNull Long id;
	private @NonNull String firstName;
	private @NonNull String lastName;
	@Column(unique = true)
	private @NonNull String userName;
	private @NonNull String password;
	@Column(unique = true)
	private @NonNull String facultyCode;

	@ManyToOne(targetEntity = College.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private @NonNull College collegeCode;

}
