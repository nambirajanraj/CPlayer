package com.stackroute.favouriteservice.exception;


@SuppressWarnings("serial")
public class PlayerNotFoundException extends Exception{
	
	public PlayerNotFoundException(){
		
	}

	private String message;

	public PlayerNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "PlayerNotFoundException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
