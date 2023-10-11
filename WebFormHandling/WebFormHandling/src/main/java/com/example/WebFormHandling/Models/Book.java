package com.example.WebFormHandling.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
	@Id
	@NotNull
	@Min(1)
	@Max(2000000000)
	private Integer id;
	
	@NotNull
	@Size(min=1, max=100)
	@Pattern(regexp = "^[a-zA-Z\s]*$")
	private String name;
	
	@NotNull
	@Size(min=1, max=35)
	@Pattern(regexp = "^[a-zA-Z\s]*$")
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
