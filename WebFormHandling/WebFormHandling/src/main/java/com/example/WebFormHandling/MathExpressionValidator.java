package com.example.WebFormHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Component
public class MathExpressionValidator implements ConstraintValidator<ValidMathExpression, String> {
	@Autowired
	CustomCaptchaData customCaptchaData;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println("\n\n\n#### Entered expre validation...\n\n\n");
		boolean existsRepeatedOperator = value.matches("[+\\-*/]+");
		boolean valid = !existsRepeatedOperator;
		
		if(valid) {
			Expression expression = new ExpressionBuilder(value).build();
		    int expressionValue = (int) expression.evaluate();
		    int customCaptchaNumberToMake = customCaptchaData.getNumberToMake();
		    System.out.println("*** Expression Value = " + expressionValue);
		    System.out.println("*** Custom Captcha Number To Make = " + customCaptchaNumberToMake);
		    valid = expressionValue == customCaptchaNumberToMake;
		}
		
		return valid;
	}
}
