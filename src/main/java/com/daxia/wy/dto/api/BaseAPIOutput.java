package com.daxia.wy.dto.api;

public class BaseAPIOutput {
	private String state = "";
	private String error = "";
    
    /**
     * @return the state
     */
    public String getState() {
    	return state;
    }

	/**
     * @param state the state to set
     */
    public void setState(String state) {
    	this.state = state;
    }

	public static MobileApiOutput error(String message) {
	    MobileApiOutput output = new MobileApiOutput();
	    output.setStatus("10");
	    output.setError(message);
	    return output;
	}

	/**
     * @return the error
     */
    public String getError() {
    	return error;
    }

	/**
     * @param error the error to set
     */
    public void setError(String error) {
    	this.error = error;
    }

	
}
