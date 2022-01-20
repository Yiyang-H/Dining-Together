package com.summerHack.diningTogether.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Application with that userId and foodId has already existed on "
    + "the server")
public class ApplicationAlreadyExistException extends Exception {
}
