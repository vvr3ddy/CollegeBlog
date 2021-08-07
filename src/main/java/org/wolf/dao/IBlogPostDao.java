package org.wolf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wolf.dto.ListBlogPostDTO;
import org.wolf.entity.BlogPost;

@Repository
public interface IBlogPostDao extends JpaRepository<BlogPost, Long> {

	@Query("select new org.wolf.dto.ListBlogPostDTO(b.id, b.postTitle, b.postDescription, b.postedBy.registrationNumber, CONCAT(b.postedBy.firstName, ' ', b.postedBy.lastName), b.creationDate, b.updateTime, b.flaggedCount, b.isApproved) from BlogPost b order by b.id")
	List<ListBlogPostDTO> listAllPosts();

	@Query("select new org.wolf.dto.ListBlogPostDTO(b.id, b.postTitle, b.postDescription, b.postedBy.registrationNumber, CONCAT(b.postedBy.firstName, ' ', b.postedBy.lastName), b.creationDate, b.updateTime, b.flaggedCount, b.isApproved) from BlogPost b where b.postedBy.userName = :userName order by b.id")
	List<ListBlogPostDTO> listByAuthor(String userName);

	@Query("select new org.wolf.dto.ListBlogPostDTO(b.id, b.postTitle, b.postDescription, b.postedBy.registrationNumber, CONCAT(b.postedBy.firstName, ' ', b.postedBy.lastName), b.creationDate, b.updateTime, b.flaggedCount, b.isApproved) from BlogPost b where b.flaggedCount > 3 order by b.id")
	List<ListBlogPostDTO> listFlaggedPosts();
}
