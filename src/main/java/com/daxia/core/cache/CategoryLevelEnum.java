package com.daxia.core.cache;


public enum CategoryLevelEnum {
	BigCategory(1, "大分类"),
	MediumgCategory(2, "中分类"),
	SmallCategory(3, "小分类");
	private int level;
	private String name;
	private CategoryLevelEnum(Integer level, String name) {
		this.level = level;
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public String getName() {
		return name;
	}

}
