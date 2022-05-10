package com.example.pilottodolist.dto;

import com.example.pilottodolist.domain.Progress;
import com.example.pilottodolist.domain.Todo;
import lombok.Builder;
import lombok.Data;

public class TodoResponseDto {

    @Data
    public static class CREATED {
        private String content;
        private Progress progress;

        public CREATED(Todo todo) {
            this.content = todo.getContent();
            this.progress = todo.getProgress();
        }
    }

    @Data
    public static class GET {
        private String content;
        private Progress progress;

        public GET(Todo todo) {
            this.content = todo.getContent();
            this.progress = todo.getProgress();
        }
    }
}
