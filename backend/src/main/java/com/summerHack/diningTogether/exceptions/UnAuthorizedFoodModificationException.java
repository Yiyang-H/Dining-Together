package com.summerHack.diningTogether.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The user does not have permission to modify status of fuod")
public class UnAuthorizedFoodModificationException extends Exception{
}

