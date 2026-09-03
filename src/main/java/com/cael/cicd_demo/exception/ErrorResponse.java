package com.cael.cicd_demo.exception;

public record ErrorResponse(int status, String message) {
}