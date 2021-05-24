package com.example.model;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {
	FinanceManager(1), Employee(2);
	
	private int value;
	private static Map map = new HashMap<>();
	
	private UserRole(int value) {
		this.value = value;
	}
	
	static {
        for (UserRole userrole : UserRole.values()) {
            map.put(userrole.value, userrole);
        }
    }

    public static UserRole valueOf(int userrole) {
        return (UserRole) map.get(userrole);
    }
	
	public int getValue() {
        return value;
    }
}
