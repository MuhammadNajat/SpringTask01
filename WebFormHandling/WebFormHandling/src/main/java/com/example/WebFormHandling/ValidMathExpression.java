package com.example.WebFormHandling;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = MathExpressionValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface ValidMathExpression {
	 String message() default "Invalid expression. Avoid invalid and easy expressions like 10+*7, 10/0, 10+0, 10-0, 10/1, 10*1. Don't use the number itself in the expression. Avoid brackets.";

	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};
}
