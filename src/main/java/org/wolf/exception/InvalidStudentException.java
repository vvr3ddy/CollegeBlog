package org.wolf.exception;

public class InvalidStudentException extends RuntimeException {

	private static final long serialVersionUID = -2016176758243219649L;

	public InvalidStudentException() {
		super();
	}

	public InvalidStudentException(String message) {
		super(message);
	}

}
