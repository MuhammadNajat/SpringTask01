package com.example.WebFormHandling.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UserInputForBookWithdrawalFromStudent {
	@NotNull
	@Min(1)
	private int bookId;
	
	@NotNull
	@Min(1)
	private int studentId;
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
