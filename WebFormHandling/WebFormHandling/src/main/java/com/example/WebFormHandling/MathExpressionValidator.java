package com.example.WebFormHandling;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("\n\n\n#### Entered expression validation...\n\n\n");
		value = value.replaceAll("\\s", "");
		
		if(value.length() == 0) {
			return false;
		}
		
		List<String> tokens = getTextTokenized(value);
		System.out.println("Tokens:" + tokens);
		
		boolean containsOnlyDigitsAndOperators = checkIfContainsOnlyDigitsAndOperators(value);
		boolean existRepeatedOperators = checkIfRepeatedOperatorsExist(value);
		boolean existEasyExpressions = checkIfEasyExpressionsExist(tokens);
		boolean startsAndEndsWithDigits = Character.isDigit(value.charAt(0)) && Character.isDigit(value.charAt(value.length()-1));
		boolean existsDivisionByZero = checkIfDivisionByZeroExists(tokens);
		boolean valid = containsOnlyDigitsAndOperators && !existRepeatedOperators && !existEasyExpressions && startsAndEndsWithDigits && !existsDivisionByZero;
		
		System.out.println("containsOnlyDigitsAndOperators:" + containsOnlyDigitsAndOperators);
		System.out.println("existRepeatedOperators:" + existRepeatedOperators);
		System.out.println("existEasyExpressions:" + existEasyExpressions);
		System.out.println("startsAndEndsWithDigits:" + startsAndEndsWithDigits);
		System.out.println("existsDivisionByZero:" + existsDivisionByZero);
		System.out.println("valid:" + valid);
		
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

	private List<String> getTextTokenized(String text) {
		int textLength = text.length();
		String operators = "+-*/";
		List<String> tokens = new ArrayList<String>();
		
		for(int i=0; i<textLength; i++) {
			if(Character.isDigit(text.charAt(i))) {
				String digits = "";
				boolean nonZeroDigitExists = false;
				while(i < textLength && Character.isDigit(text.charAt(i))) {
					digits = digits + text.charAt(i);
					nonZeroDigitExists |= (text.charAt(i) != '0');
					i++;
				}
				i--;
				//If the string digit has multiple 0s and no other digits, than consider it as only a single 0
				digits = nonZeroDigitExists? digits : "0";
				tokens.add(digits);
			}
			else if(operators.indexOf( text.charAt(i) ) != -1) {
				tokens.add("" + text.charAt(i));
			}
		}
		return tokens;
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
	
	//Easy expressions are assumed as follows: + 0, - 0 , * 1, and / 1
	private boolean checkIfEasyExpressionsExist(List<String> tokens) {
		int tokenCount = tokens.size();
		boolean exist = false;
		for(int i=0; i<tokenCount-1; i++) {
			String token = tokens.get(i);
			String nextToken = tokens.get(i+1);
			exist |= (token.equals("+") && nextToken.equals("0"));
			exist |= (token.equals("-") && nextToken.equals("0"));
			exist |= (token.equals("*") && nextToken.equals("1"));
			exist |= (token.equals("/") && nextToken.equals("1"));
		}
		
		return exist;
	}
	
	private boolean checkIfDivisionByZeroExists(List<String> tokens) {
		int tokenCount = tokens.size();
		boolean exist = false;
		for(int i=0; i<tokenCount-1; i++) {
			String token = tokens.get(i);
			String nextToken = tokens.get(i+1);
			System.out.println("token: " + token + ".");
			System.out.println("nextToken: " + nextToken + ".");
			exist |= (token.equals("/") && nextToken.equals("0"));
		}
		
		return exist;
	}
}
