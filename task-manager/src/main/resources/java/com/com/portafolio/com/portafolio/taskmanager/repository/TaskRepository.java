package com.portfolio.taskmanager.repository;

import com.portfolio.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // JpaRepository ya trae métodos listos como save(), findAll(), findById(), deleteById(), etc.
}