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
		value = value.replaceAll("\\s", "");
		
		boolean existsRepeatedOperators = checkIfRepeatedOperatorsExist(value);
		boolean containsOnlyDigitsAndOperators = checkIfContainsOnlyDigitsAndOperators(value);
		boolean startsAndEndsWithDigits = Character.isDigit(value.charAt(0)) && Character.isDigit(value.charAt(value.length()-1));
		boolean valid = !existsRepeatedOperators && containsOnlyDigitsAndOperators && startsAndEndsWithDigits;
		
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

	private boolean checkIfRepeatedOperatorsExist(String text) {
		int textLength = text.length();
		boolean response = false;
		String operators = "+-*/";
		for(int i=0; i<textLength-1; i++) {
			Character character = text.charAt(i);
			char nextCharacter = text.charAt(i+1);
			response |= (operators.indexOf(character) != -1 && operators.indexOf(nextCharacter) != -1);
		}
		return response;
	}

	private boolean checkIfContainsOnlyDigitsAndOperators(String text) {
		int textLength = text.length();
		boolean response = true;
		String operators = "+-*/";
		for(int i=0; i<textLength; i++) {
			char character = text.charAt(i);
			boolean valid = Character.isDigit(character);
			valid |= operators.indexOf(character) != -1 ;
			response &= valid;
		}
		return response;
	}
}
