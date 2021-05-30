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
public class Student implements Serializable {
	@Id
	@Column(name = "reg_num", unique = true)
	private String registrationNumber;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String userName;
	private String password;

	@OneToMany(mappedBy = "postedBy", targetEntity = BlogPost.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<BlogPost> blogPosts;

	@ManyToOne(targetEntity = College.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "college_code")
	private College collegeCode;

}
