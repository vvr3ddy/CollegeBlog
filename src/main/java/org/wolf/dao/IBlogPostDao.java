package org.wolf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wolf.dto.BlogPostDTO;
import org.wolf.entity.BlogPost;

@Repository
public interface IBlogPostDao extends JpaRepository<BlogPost, Long> {

	@Query("select new org.wolf.dto.BlogPostDTO(b.postTitle, b.postDescription, b.isApproved, CONCAT(b.postedBy.firstName, ' ', b.postedBy.lastName)) from BlogPost b where b.postedBy.userName = :userName order by b.id")
	List<BlogPostDTO> listByAuthor(String userName);

	@Query("select new org.wolf.dto.BlogPostDTO(b.postTitle, b.postDescription, b.isApproved, CONCAT(b.postedBy.firstName, ' ', b.postedBy.lastName)) from BlogPost b where b.flaggedCount > 3 order by b.id")
	List<BlogPostDTO> listFlaggedPosts();

	@Query("select new org.wolf.dto.BlogPostDTO(b.postTitle, b.postDescription, b.isApproved, CONCAT(b.postedBy.firstName, ' ', b.postedBy.lastName)) from BlogPost b order by b.id")
	List<BlogPostDTO> listAllPosts();
}
