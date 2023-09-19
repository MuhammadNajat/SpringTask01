package com.example.WebFormHandling;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class UserInput {
	@NotNull
	@Size(min=3, max=30)
	@Pattern(regexp = "^[a-zA-Z\s]*$")
	private String name;
	
	@NotNull
	@Min(15)
	private int age;
	
	@NotNull
	@NotEmpty
	@Email
	private String emailAddress;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[0-9+\\-*/]+$", message = "Should only consist of digits and operators")
	@Pattern(regexp = "^[0-9][0-9+\\-*/]*[0-9]$", message = "Should start and end with digits")
	@ValidMathExpression
	private String expression;

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
}
