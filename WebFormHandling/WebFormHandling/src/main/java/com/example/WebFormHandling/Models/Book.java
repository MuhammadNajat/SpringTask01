package com.example.WebFormHandling.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Min(1)
	@Max(2000000000)
	@JsonProperty("id")
	private Long id;
	
	@NotNull
	@Size(min=1, max=100)
	@Pattern(regexp = "^[a-zA-Z\s]*$")
	@JsonProperty("name")
	private String name;
	
	@NotNull
	@Size(min=1, max=35)
	@Pattern(regexp = "^[a-zA-Z\s]*$")
	@JsonProperty("writerName")
	private String writerName;
	
	@ManyToOne
	@JsonProperty("borrowerStudent")
	private Student borrowerStudent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
