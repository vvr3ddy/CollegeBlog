package org.wolf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wolf.dto.BlogPostDTO;
import org.wolf.entity.BlogPost;

@Service
public interface IBlogPostService {
	
	public BlogPost addPost(BlogPostDTO blogPostDto);
	
	public BlogPost updatePost(Long postId, BlogPostDTO blogPostDto);
	
	public BlogPost approvePost(Long postId);
	
	public void flagPost(Long postId, String facultyCode);
	
	public void deleteBlogPost(Long postId);
	
	public List<BlogPostDTO> findPostsByAuthor(String userName);
	
	public List<BlogPostDTO> listFlaggedPosts();
	
	public List<BlogPostDTO> listAllPosts();

}
