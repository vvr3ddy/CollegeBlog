package org.wolf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wolf.dto.BlogPostDTO;
import org.wolf.entity.BlogPost;

@Service
public interface IBlogPostService {
	
	public BlogPost addPost(BlogPostDTO blogPostDto);
	
	public BlogPost approvePost(Long postId);
	
	public void deleteBlogPost(Long postId);
	
	public List<BlogPostDTO> findPostsByAuthor(String userName);
	
	public void flagPost(Long postId, String facultyCode);
	
	public List<BlogPostDTO> listAllPosts();
	
	public List<BlogPostDTO> listFlaggedPosts();
	
	public BlogPost updatePost(Long postId, BlogPostDTO blogPostDto);

}
