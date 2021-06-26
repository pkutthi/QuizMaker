package com.navigus.quizmaker.controller.rest.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.navigus.quizmaker.controller.utils.ErrorInfo;

@ControllerAdvice("com.navigus.quizmaker.controller.rest.v1")
public class RestExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorInfo unauthorizedAction(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}
