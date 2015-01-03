package com.daxia.core.cache;

public class CtxCache {
	private static CtxCache instance = new CtxCache();
	private String ctx;
	private CtxCache() {
	}
	
	public static CtxCache getInstance() {
		return instance;
	}
	
	public String getCtx() {
		return ctx;
	}
	
	public void setCtx(String ctx) {
		this.ctx = ctx;
	}
}
