package com.lec.java.access02;

public class Test02 {

	private int privateNum;
	int defaultNum;
	protected int protectedNum;
	public int publicNum;
	
	private void privateMethod() {
		System.out.println("Private method");
	}
	
	void defaultMethod() {
		System.out.println("Default method");
	}
	
	protected void protectedMethod() {
		System.out.println("Protected method");
	}
	
	public void publicMethod() {
		System.out.println("Public method");
	}
	
	
}
