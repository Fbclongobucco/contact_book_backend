package com.buccodev.contact_book.infrastructure.controllers.exception.dtos_response;

import java.util.List;
import java.util.Map;

public record BeanValidationErrorResponse(String timeStamp, Integer status, List<Map<String, String>> errors, String path) {
}
