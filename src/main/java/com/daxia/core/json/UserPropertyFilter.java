package com.daxia.core.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class UserPropertyFilter implements PropertyFilter {

	private static List<String> applyList = new ArrayList<String>();
	static {
		applyList.add("id");
	    applyList.add("username");
	    applyList.add("nickName");
	    applyList.add("mobile");
	    applyList.add("userType");
	}
	
	@Override
	public boolean apply(Object source, String name, Object value) {
		return applyList.contains(name);
	}

}
