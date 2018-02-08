package org.title21.utility;

import java.util.UUID;
import java.util.Random;

public class FunctionUtils {	
	
	public static String generateString() 
	{	 
		String uuid = UUID.randomUUID().toString();
		return uuid;	 
	}	

	public static String generateRandomNumber(){
		
		Random randomGenerator = new Random();	   
	    int randomInt = randomGenerator.nextInt(1000);	    
	    return String.valueOf(randomInt);	    	
	}
	
}
