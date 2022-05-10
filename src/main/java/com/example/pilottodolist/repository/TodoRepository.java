package com.example.pilottodolist.repository;

import com.example.pilottodolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
