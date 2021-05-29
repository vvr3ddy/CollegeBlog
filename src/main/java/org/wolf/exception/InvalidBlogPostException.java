package org.wolf.exception;

public class InvalidBlogPostException extends RuntimeException {

	private static final long serialVersionUID = -1991584002565466677L;

	public InvalidBlogPostException() {
		super();
	}

	public InvalidBlogPostException(String message) {
		super(message);
	}

}
