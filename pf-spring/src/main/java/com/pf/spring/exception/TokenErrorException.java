package com.dindon.core.exception;

public class TokenErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TokenErrorException(){
		super();
	}

	public TokenErrorException(String key){
		super("user token error [" + key + "]");
	}
	
}
