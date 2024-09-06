package com.buccodev.contact_book.resources.excepetions;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {
        private static final long serialVersionUID = 1L;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        private Instant timeamp;
        private Integer status;
        private String error;
        private String message;
        private String path;

        public StandardError() {
        }

        public StandardError(Instant timeamp, Integer status, String error, String message, String path) {
            super();
            this.timeamp = timeamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }

        public Instant getTimeamp() {
            return timeamp;
        }

        public void setTimeamp(Instant timeamp) {
            this.timeamp = timeamp;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }


    }
