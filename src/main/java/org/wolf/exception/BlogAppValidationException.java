package org.wolf.exception;

import java.util.List;

public class BlogAppValidationException extends RuntimeException {

	private static final long serialVersionUID = -1381510345423867578L;
	private List<String> messages;

	public BlogAppValidationException() {
		super();
	}

	public BlogAppValidationException(List<String> messages) {
		super();
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

}
