package com.alolorsus.util;

import java.util.LinkedHashMap;

@SuppressWarnings("rawtypes")
public class JsonMap extends LinkedHashMap {
	
	public JsonMap json(String key) {
		return (JsonMap) super.get(key);
	}

	private static final long serialVersionUID = 1L;
}
