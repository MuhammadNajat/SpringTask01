package com.example.WebFormHandling.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "writerName")
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
