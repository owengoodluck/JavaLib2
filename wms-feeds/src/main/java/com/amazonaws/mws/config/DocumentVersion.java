package com.amazonaws.mws.config;

public enum DocumentVersion {
	version1_01(1.01);
	
	private double value;
	private DocumentVersion(double value){
		this.value = value	;
	}
	
	public String toString(){
		return String.valueOf(this.value);
	}
}
