package com.dihaw.entity;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 2373562273475783733L;
	
	private String name, email;
	private int age;
	
	public Person(String name, String email, int age) {
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String toString(){ 
		return String.format("Person [name = %s, email = %s, age = %d]", name, email, age);
	}
	
}

