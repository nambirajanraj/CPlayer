package com.stackroute.favouriteservice.exception;


@SuppressWarnings("serial")
public class PlayerAlreadyExistsException extends Exception{
	
	public PlayerAlreadyExistsException(){
		
	}
	
	private String message;

	@Override
	public String toString() {
		return "PlayerAlreadyExistsException [message=" + message + "]";
	}

	public PlayerAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
