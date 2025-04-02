package com.buccodev.contact_book.infrastructure.controllers.exception.dtos_response;

public record StandardError(String timeStamp, Integer status, String error, String path){
}
