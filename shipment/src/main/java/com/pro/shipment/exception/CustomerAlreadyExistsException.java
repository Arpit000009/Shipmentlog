package com.pro.shipment.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
	public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
