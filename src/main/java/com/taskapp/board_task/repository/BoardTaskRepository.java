package com.taskapp.board_task.repository;

import com.taskapp.board_task.entity.BoardTask;
import com.taskapp.board_task.enums.BoardTaskState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoardTaskRepository extends JpaRepository<BoardTask, UUID> {
    List<BoardTask> findByBoardId(UUID boardId);
    List<BoardTask> findByAssigneeId(UUID assigneeId);
    List<BoardTask> findByBoardIdAndState(UUID boardId, BoardTaskState state);
}
