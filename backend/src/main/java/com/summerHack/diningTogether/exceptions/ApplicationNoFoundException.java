package com.summerHack.diningTogether.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Application with that id was not found on the server")
public class ApplicationNoFoundException extends Exception{
}


