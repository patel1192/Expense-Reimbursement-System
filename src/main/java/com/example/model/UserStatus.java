package com.example.model;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {
	Approved(1), Pending(2),Denied(3);
	
	private int value;
	private static Map map = new HashMap<>();
	
	private UserStatus(int value) {
		this.value = value;
	}
	
	static {
        for (UserStatus userstatus : UserStatus.values()) {
            map.put(userstatus.value, userstatus);
        }
    }

    public static UserStatus valueOf(int userstatus) {
        return (UserStatus) map.get(userstatus);
    }
	
	public int getValue() {
        return value;
    }
}
