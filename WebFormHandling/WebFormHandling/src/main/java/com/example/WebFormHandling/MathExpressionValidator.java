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
		
		boolean containsOnlyDigitsAndOperators = checkIfContainsDigitsAndOperatorsOnly(value);
		boolean containsRepeatedOperators = checkIfContainsRepeatedOperators(value);
		boolean containsEasyExpressions = checkIfContainsEasyExpressions(tokens);
		boolean startsAndEndsWithDigits = Character.isDigit(value.charAt(0)) && Character.isDigit(value.charAt(value.length()-1));
		boolean containsDivisionByZero = checkIfContainsDivisionByZero(tokens);
		boolean containsNumberToMake = checkIfContainsNumberToMake(tokens, String.valueOf(customCaptchaData.getNumberToMake()));
		boolean valid = containsOnlyDigitsAndOperators && !containsRepeatedOperators && !containsEasyExpressions && startsAndEndsWithDigits && !containsDivisionByZero && !containsNumberToMake;
		
		//Debug codes
		System.out.println("containsOnlyDigitsAndOperators:" + containsOnlyDigitsAndOperators);
		System.out.println("containsRepeatedOperators:" + containsRepeatedOperators);
		System.out.println("containsEasyExpressions:" + containsEasyExpressions);
		System.out.println("startsAndEndsWithDigits:" + startsAndEndsWithDigits);
		System.out.println("containsDivisionByZero:" + containsDivisionByZero);
		System.out.println("numberToMakeExistsInTokens:" + containsNumberToMake);
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
				
				//Ignore leading zeros
				while(i < textLength && Character.isDigit(text.charAt(i))) {
					if(text.charAt(i) != '0') {
						break;
					}
					i++;
				}
				
				//Consider non-zero digits
				while(i < textLength && Character.isDigit(text.charAt(i))) {
					digits = digits + text.charAt(i);
					i++;
				}
				i--;
				
				//If the string digit is empty after ignoring the 0s in the text, then string digit should have only one 0 instead of multiple 0s
				digits = digits.equals("")? "0" : digits;
				tokens.add(digits);
			}
			else if(operators.indexOf( text.charAt(i) ) != -1) {
				tokens.add("" + text.charAt(i));
			}
		}
		return tokens;
	}

	private boolean checkIfContainsRepeatedOperators(String text) {
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

	private boolean checkIfContainsDigitsAndOperatorsOnly(String text) {
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
	private boolean checkIfContainsEasyExpressions(List<String> tokens) {
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
	
	private boolean checkIfContainsDivisionByZero(List<String> tokens) {
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
	
	private boolean checkIfContainsNumberToMake(List<String> tokens, String reference) {
		boolean exist = false;
		for(String token : tokens) {
			exist |= token.equals(reference);
		}
		
		return exist;
	}
}
