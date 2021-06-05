package com.alolorsus.util;

import java.util.LinkedHashMap;

@SuppressWarnings("rawtypes")
public class JsonMap extends LinkedHashMap {
	
	public JsonMap json(String key) {
		return (JsonMap) super.get(key);
	}
	
	public String str(String key) {
		return (String) super.get(key);
	}
	
	private static final long serialVersionUID = 1L;
}
