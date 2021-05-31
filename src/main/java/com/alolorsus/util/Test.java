package com.alolorsus.util;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public Test() {
		Map<String, Object> a  = new HashMap<>();
		
		JsonMap b = (JsonMap) a;
		
		b.json("a").get("c");
	}
	
}
