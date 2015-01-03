package com.daxia.core.security;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	
	public static void main(String[] args) throws IOException {
	    // D:\cpp\mycpp\main.app
	    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\cpp\\mycpp\\main.app")));
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	        System.out.println(line);
	    }
	    
	}
    
}
