package com.example.model;

import java.util.HashMap;
import java.util.Map;

public enum ErsType {
	Travel(1), Food(2),Other(3);
	
	private int value;
	private static Map map = new HashMap<>();
	
	private ErsType(int value) {
		this.value = value;
	}
	
	static {
        for (ErsType erstype : ErsType.values()) {
            map.put(erstype.value, erstype);
        }
    }

    public static ErsType valueOf(int erstype) {
        return (ErsType) map.get(erstype);
    }
	
	public int getValue() {
        return value;
    }
}
