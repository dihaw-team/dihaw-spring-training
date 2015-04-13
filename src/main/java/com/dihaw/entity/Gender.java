package com.dihaw.entity;

public enum Gender {

	Male,
	Female;
	
    public String value() {
        return name();
    }

    public static Gender fromValue(String v) {
        return valueOf(v);
    }
}
