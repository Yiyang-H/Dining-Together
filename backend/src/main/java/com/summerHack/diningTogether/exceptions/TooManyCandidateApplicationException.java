package com.summerHack.diningTogether.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The candidate makes too many applications simultaneously")
public class TooManyCandidateApplicationException extends Exception {
}
