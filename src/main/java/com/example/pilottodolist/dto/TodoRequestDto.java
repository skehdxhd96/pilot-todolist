package com.example.pilottodolist.dto;

import lombok.Data;

public class TodoRequestDto {

    @Data
    public static class CREATED {
        private String content;
    }
}
