package org.wolf.entity;

import java.time.LocalDateTime;

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
public class BlogPost {
	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_generator")
	@SequenceGenerator(name = "post_generator", sequenceName = "post_seq", allocationSize = 1)
	private @NonNull Long id;
	private @NonNull String postTitle;
	private @NonNull String postDescription;
	private @NonNull LocalDateTime creationDate;
	private @NonNull LocalDateTime updateTime;
	private @NonNull Boolean isApproved;

	@ManyToOne(targetEntity = Student.class, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private @NonNull Student postedBy;

}
