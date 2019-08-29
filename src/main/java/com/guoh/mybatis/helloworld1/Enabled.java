package com.guoh.mybatis.helloworld1;

public enum Enabled {
	disabled(1), enabled(0);
	
	private final int value;
	
	private Enabled(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}
