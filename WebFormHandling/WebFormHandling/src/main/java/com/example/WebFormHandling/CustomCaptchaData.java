package com.example.WebFormHandling;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CustomCaptchaData {
	private static int upperBound = 999;
	private static int numberToMake;
	
	static {
		Random random = new Random();
		numberToMake = random.nextInt(upperBound) + 1;
	}
	
	public CustomCaptchaData() {
	}

	public int getNumberToMake() {
		return numberToMake;
	}

	public void setNumberToMake(int numberToMake) {
		CustomCaptchaData.numberToMake = numberToMake;
	} 
}
