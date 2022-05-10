package com.example.pilottodolist.service;

import com.example.pilottodolist.domain.Progress;
import com.example.pilottodolist.domain.Todo;
import com.example.pilottodolist.dto.TodoListInfo;
import com.example.pilottodolist.dto.TodoRequestDto;
import com.example.pilottodolist.dto.TodoResponseDto;
import com.example.pilottodolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class TodoService {

    private final TodoRepository repository;

    /**
     *  TodoList 생성
     */
    @Transactional
    public TodoResponseDto.CREATED create(TodoRequestDto.CREATED created) {

        Todo todo = repository.save(Todo.builder()
                                        .content(created.getContent())
                                        .progress(Progress.ACTIVE)
                                        .build());

        return new TodoResponseDto.CREATED(todo);
    }

    /**
     *  전체 TodoList 조회
     */
    public List<TodoResponseDto.GET> getTodoList() {

        return repository.findAll()
                .stream().map(t -> new TodoResponseDto.GET(t)).collect(Collectors.toList());
    }

    /**
     *  Active <-> Completed 상태 변화
     */
    @Transactional
    public void changeProgress(Long id) {

        /**
         * 동시성 고려
         * 수정 중 누가 지울수도 있고, 수정할 수도 있음
         */
        log.info("=========== 함수실행전 현재 스레드 {}================", Thread.currentThread());
        repository.findById(id)
                .orElseThrow(() -> new OptimisticLockingFailureException("동시 접근 중 입니다.")).changeProgress();
        log.info("=========== 함수실행후 현재 스레드 {}================", Thread.currentThread());
    }
}
