package com.taskapp.board_invitation.entity;

import com.taskapp.board.entity.Board;
import com.taskapp.board.enums.BoardMemberRole;
import com.taskapp.board_invitation.enums.BoardInvitationStatus;
import com.taskapp.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "board_invitations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode( of = "id")
@ToString( exclude = { "board", "invitedBy", "invitedUser" })
@Builder
public class BoardInvitation {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_by", nullable = false)
    private User invitedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_user", nullable = false)
    private User invitedUser;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private BoardMemberRole role;

    @Column(nullable = false, length = 255, unique = true)
    private String token;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private BoardInvitationStatus status;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
