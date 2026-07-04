package com.taskapp.board_invitation.repository;

import com.taskapp.board_invitation.entity.BoardInvitation;
import com.taskapp.board_invitation.enums.BoardInvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoardInvitationRepository extends JpaRepository<BoardInvitation, UUID> {
    Optional<BoardInvitation> findByToken(String token);
    List<BoardInvitation> findByInvitedUserIdAndStatus(UUID userId, BoardInvitationStatus status);
    @Query("""
    SELECT bi FROM BoardInvitation bi
    WHERE bi.invitedUser.id = :userId
    AND bi.status = 'PENDING'
    AND bi.expiresAt > CURRENT_TIMESTAMP
    """)
    List<BoardInvitation> findActivePendingInvitations(@Param("userId") UUID userId);
}
