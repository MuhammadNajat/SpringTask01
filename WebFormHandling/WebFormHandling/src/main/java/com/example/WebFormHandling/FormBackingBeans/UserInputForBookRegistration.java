package com.example.WebFormHandling.FormBackingBeans;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserInputForBookRegistration {
	@NotNull
	@Min(1)
	@Max(2000000000)
	private int id;
	
	@NotNull
	@Size(min=1, max=100)
	@Pattern(regexp = "^[a-zA-Z\s]*$")
	private String name;
	
	@NotNull
	@Size(min=1, max=35)
	@Pattern(regexp = "^[a-zA-Z\s]*$")
	private String writerName;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWriterName() {
		return this.writerName;
	}
	
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
}
