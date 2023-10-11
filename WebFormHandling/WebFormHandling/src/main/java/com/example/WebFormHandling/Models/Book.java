package com.example.WebFormHandling.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	private Integer id;
	
	private String name;
	
	private String writerName;
	
	@ManyToOne
	private Student borrowerStudent;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public Student getBorrowerStudent() {
		return borrowerStudent;
	}
	public void setBorrowerStudent(Student borrowerStudent) {
		this.borrowerStudent = borrowerStudent;
	}
	
}
