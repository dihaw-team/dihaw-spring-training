package com.dihaw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CITY")
public class City{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CITY_ID", nullable = false)
	private int cityId;
	
	@Column(name="CITY_NAME", nullable = false)
	private String cityName;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public City(){
	}
	
	public City(String cityName){
		this.cityName=cityName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
