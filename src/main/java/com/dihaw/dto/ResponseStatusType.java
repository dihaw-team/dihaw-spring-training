package com.dihaw.dto;

public enum ResponseStatusType {

	SUCCESS,
    WRONG_DATA,
    DUPLICATE_DATA,
    USER_EXIST;

    public String value() {
        return name();
    }

    public static ResponseStatusType fromValue(String v) {
        return valueOf(v);
    }

}
