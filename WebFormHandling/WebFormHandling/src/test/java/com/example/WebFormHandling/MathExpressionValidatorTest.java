package com.example.WebFormHandling;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

class MathExpressionValidatorTest {

	@Test
	void test() {
		//fail("Not yet implemented");
		//This was auto generated
	}
	
	@Test
	void testCheckIfRepeatedOperatorsExist_01() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MathExpressionValidator.class.getDeclaredMethod("checkIfRepeatedOperatorsExist", String.class);
	    method.setAccessible(true);
	    assertEquals(false, method.invoke(new MathExpressionValidator(), "1+5"));
	}
	
	@Test
	void testCheckIfRepeatedOperatorsExist_02() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MathExpressionValidator.class.getDeclaredMethod("checkIfRepeatedOperatorsExist", String.class);
	    method.setAccessible(true);
	    assertEquals(true, method.invoke(new MathExpressionValidator(), "1+*5"));
	}
	
	@Test
	void testCheckIfRepeatedOperatorsExis_03() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MathExpressionValidator.class.getDeclaredMethod("checkIfRepeatedOperatorsExist", String.class);
	    method.setAccessible(true);
	    assertEquals(true, method.invoke(new MathExpressionValidator(), "1-*5"));
	}
	
	@Test
	void testCheckIfRepeatedOperatorsExist_04() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MathExpressionValidator.class.getDeclaredMethod("checkIfRepeatedOperatorsExist", String.class);
	    method.setAccessible(true);
	    assertEquals(true, method.invoke(new MathExpressionValidator(), "1+5+10++9"));
	}
	
	@Test
	void testCheckIfRepeatedOperatorsExist_05() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MathExpressionValidator.class.getDeclaredMethod("checkIfRepeatedOperatorsExist", String.class);
	    method.setAccessible(true);
	    assertEquals(false, method.invoke(new MathExpressionValidator(), "50-30+10/5"));
	}
	
	@Test
	void testCheckIfContainsOnlyDigitsAndOperators_01() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MathExpressionValidator.class.getDeclaredMethod("checkIfContainsOnlyDigitsAndOperators", String.class);
	    method.setAccessible(true);
	    assertEquals(true, method.invoke(new MathExpressionValidator(), "50-30+10/5"));
	}
	
	@Test
	void testCheckIfContainsOnlyDigitsAndOperators_02() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MathExpressionValidator.class.getDeclaredMethod("checkIfContainsOnlyDigitsAndOperators", String.class);
	    method.setAccessible(true);
	    assertEquals(false, method.invoke(new MathExpressionValidator(), "50-30+10/5."));
	}
	
	@Test
	void testCheckIfContainsOnlyDigitsAndOperators_03() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = MathExpressionValidator.class.getDeclaredMethod("checkIfContainsOnlyDigitsAndOperators", String.class);
	    method.setAccessible(true);
	    assertEquals(false, method.invoke(new MathExpressionValidator(), "5+A-3"));
	}
}
