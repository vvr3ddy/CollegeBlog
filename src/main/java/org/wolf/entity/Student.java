package org.wolf.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Student {

	@Id
	@Column(name = "stu_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")
	@SequenceGenerator(name = "student_generator", sequenceName = "student_seq", allocationSize = 1)
	private @NonNull Long id;
	private @NonNull String firstName;
	private @NonNull String lastName;
	@Column(unique = true)
	private @NonNull String userName;
	private @NonNull String password;
	@Column(unique = true)
	private @NonNull String registrationNumber;

	@OneToMany(mappedBy = "post_id", targetEntity = BlogPost.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private @NonNull List<BlogPost> blogPosts;

	@ManyToOne(targetEntity = College.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private @NonNull College collegeCode;
	
	@ManyToOne(targetEntity = Faculty.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private @NonNull Faculty facultyCode;
}
