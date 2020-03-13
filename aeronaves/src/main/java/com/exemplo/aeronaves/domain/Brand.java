package com.exemplo.aeronaves.domain;

public enum Brand {
	EMBRAER("EMBRAER"),
	BOEING("BOEING"),
	AIRBUS("AIRBUS");
	
	private String displayName;
	
	Brand(final String displayName){
		this.displayName = displayName;
	}
	
	@Override
	public String toString() {
		return this.displayName;
	}
}
