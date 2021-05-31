package org.wolf.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogPost implements Serializable {

	private static final long serialVersionUID = -6460973366324042571L;

	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_generator")
	@SequenceGenerator(name = "post_generator", sequenceName = "post_seq", allocationSize = 1)
	private Long id;
	private String postTitle;
	private String postDescription;
	private LocalDateTime creationDate;
	private LocalDateTime updateTime;
	@Column(columnDefinition = "boolean default 0")
	private Boolean isApproved;

	private Integer flaggedCount;

	@ManyToOne(targetEntity = Student.class, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "student_code")
	private Student postedBy;

}
