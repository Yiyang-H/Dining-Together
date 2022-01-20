package com.summerHack.diningTogether.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The user does not have permission to access this user")
public class UnAuthorizedUserAccessException extends Exception {
}
