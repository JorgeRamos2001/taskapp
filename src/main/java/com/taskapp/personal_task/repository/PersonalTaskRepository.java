package com.taskapp.personal_task.repository;

import com.taskapp.personal_task.entity.PersonalTask;
import com.taskapp.personal_task.enums.PersonalTaskState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonalTaskRepository extends JpaRepository<PersonalTask, UUID> {
    List<PersonalTask> findByOwnerId(UUID ownerId);
    List<PersonalTask> findByOwnerIdAndState(UUID ownerId, PersonalTaskState state);
}
