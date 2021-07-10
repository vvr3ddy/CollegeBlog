package org.wolf.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wolf.dao.IBlogPostDao;
import org.wolf.dao.IFacultyDao;
import org.wolf.dao.IStudentDao;
import org.wolf.dto.BlogPostDTO;
import org.wolf.dto.ListBlogPostDTO;
import org.wolf.entity.BlogPost;
import org.wolf.exception.InvalidBlogPostException;
import org.wolf.service.IBlogPostService;

@Service
public class BlogPostServiceImpl implements IBlogPostService {

	@Autowired
	IBlogPostDao blogPostDao;

	@Autowired
	IStudentDao studentDao;

	@Autowired
	IFacultyDao facultyDao;

	@Override
	public BlogPost addPost(BlogPostDTO blogPostDto) {
		if (!studentDao.existsById(blogPostDto.getCreatedBy())) {
			throw new InvalidBlogPostException();
		} else {
			BlogPost blogPost = new BlogPost();

			blogPost.setPostTitle(blogPostDto.getPostTitle());
			blogPost.setPostDescription(blogPostDto.getPostDescription());
			blogPost.setIsApproved(false);
			blogPost.setPostedBy(studentDao.findById(blogPostDto.getCreatedBy()).get());
			blogPost.setCreationDate(LocalDateTime.now());
			blogPost.setUpdateTime(LocalDateTime.now());
			blogPost.setFlaggedCount(0);
			return blogPostDao.save(blogPost);
		}

	}

	@Override
	public BlogPost approvePost(Long postId) {
		if (blogPostDao.existsById(postId)) {
			BlogPost blog = blogPostDao.findById(postId).get();
			blog.setIsApproved(true);
			return blogPostDao.save(blog);
		} else {
			throw new InvalidBlogPostException();
		}
	}

	@Override
	public void deleteBlogPost(Long postId) {
		if (blogPostDao.existsById(postId)) {
			blogPostDao.deleteById(postId);
		} else {
			throw new InvalidBlogPostException();
		}

	}

	@Override
	public List<ListBlogPostDTO> findPostsByAuthor(String userName) {
		if (studentDao.existsByUserName(userName)) {
			return blogPostDao.listByAuthor(userName);
		} else {
			throw new InvalidBlogPostException();
		}
	}

	@Override
	public void flagPost(Long postId, String facultyCode) {
		if (blogPostDao.existsById(postId) && facultyDao.existsById(facultyCode)) {
			BlogPost post = blogPostDao.findById(postId).get();
			post.setFlaggedCount(post.getFlaggedCount() + 1);
			blogPostDao.save(post);
		} else
			throw new InvalidBlogPostException();

	}

	@Override
	public List<ListBlogPostDTO> listAllPosts() {
		return blogPostDao.listAllPosts();
	}

	@Override
	public List<ListBlogPostDTO> listFlaggedPosts() {
		return blogPostDao.listFlaggedPosts();
	}

	@Override
	public BlogPost updatePost(Long postId, BlogPostDTO blogPostDto) {
		if (blogPostDao.existsById(postId)) {
			BlogPost blogPost = blogPostDao.findById(postId).get();
			blogPost.setIsApproved(false);
			blogPost.setPostTitle(blogPostDto.getPostTitle());
			blogPost.setPostDescription(blogPostDto.getPostDescription());
			blogPost.setUpdateTime(LocalDateTime.now());
			blogPost.setFlaggedCount(0);
			return blogPostDao.save(blogPost);
		} else {
			throw new InvalidBlogPostException();
		}
	}

}
