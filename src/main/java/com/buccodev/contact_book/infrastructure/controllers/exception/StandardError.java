package com.buccodev.contact_book.infrastructure.controllers.exception;

import java.util.List;
import java.util.Map;

public record StandardError(String timeStamp, Integer status, String path, List<Map<String, String>> erros){
}
