package com.daxia.core.web.cart;

import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 */
public class Cart {
	private Map<Long, Integer> itemMap = new HashMap<Long, Integer>();

	public Map<Long, Integer> getItemMap() {
    	return itemMap;
    }

	public void setItemMap(Map<Long, Integer> itemMap) {
    	this.itemMap = itemMap;
    }
	
}
