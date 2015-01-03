package com.daxia.core.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Common cache, used for cache something.
 * 
 * Sep 1, 2012
 *
 */
public class CommonCache {
	private Map<CommonCacheKey, Object> map;
	
	private static class InstanceHolder {
		private static CommonCache instance = new CommonCache();
	}
	
	public static CommonCache getInstance() {
		return InstanceHolder.instance;
	}
	
	public CommonCache() {
		init();
    }

	private void init() {
		map = new HashMap<CommonCacheKey, Object>();
    }
	
	public Object get(CommonCacheKey key) {
		return map.get(key);
	}
	
	public void put(CommonCacheKey key, Object value) {
		map.put(key, value);
	}
}
