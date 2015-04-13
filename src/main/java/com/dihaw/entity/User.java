package com.dihaw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;
	
	@Column(name = "FIRST_NAME")
	@NotBlank
	@Size(min = 3, max = 10)
	private String firstName;

	@Column(name = "LAST_NAME")
	@NotBlank
	@Size(min = 3, max = 10)
	private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "CITY_ID")
	private City city;

	/**
     * Protected constructor for ORM.
     */
	public User() {
    }
	
    public User(String firstName, String lastName, 
    			Gender gender, City city){
    	
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.gender=gender;
    	this.city=city;
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}