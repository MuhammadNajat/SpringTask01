package com.example.WebFormHandling;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	@Id
	private Integer id;
	private String name;
	private String writer;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Student getBorrowerStudent() {
		return borrowerStudent;
	}
	public void setBorrowerStudent(Student borrowerStudent) {
		this.borrowerStudent = borrowerStudent;
	}
	
}
