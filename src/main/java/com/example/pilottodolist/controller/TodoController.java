package com.example.pilottodolist.controller;

import com.example.pilottodolist.dto.TodoListInfo;
import com.example.pilottodolist.dto.TodoRequestDto;
import com.example.pilottodolist.dto.TodoResponseDto;
import com.example.pilottodolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo")
    public TodoResponseDto.CREATED createTodo(@RequestBody TodoRequestDto.CREATED created) {
        return todoService.create(created);
    }

    @GetMapping("/todoList")
    public TodoListInfo<TodoResponseDto.GET> getTodoList() {
        List<TodoResponseDto.GET> todoList = todoService.getTodoList();
        return new TodoListInfo<>(todoList.size(), todoList);
    }

    @PostMapping("/{todoId}/progress")
    public void changeProgress(@PathVariable("todoId") Long id) {
        todoService.changeProgress(id);
    }
}
