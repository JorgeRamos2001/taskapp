package com.taskapp.user.entity;

import com.taskapp.board.entity.Board;
import com.taskapp.board.entity.BoardMember;
import com.taskapp.board_invitation.entity.BoardInvitation;
import com.taskapp.board_task.entity.BoardTask;
import com.taskapp.personal_task.entity.PersonalTask;
import com.taskapp.user.enums.UserProvider;
import com.taskapp.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode( of = "id")
@ToString( exclude = { "password", "boards", "boardMembers", "boardTasks", "personalTasks", "invitations" })
@Builder
public class User {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "url_avatar", length = 255)
    private String urlAvatar;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserProvider provider;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoardMember> boardMembers = new ArrayList<>();

    @OneToMany(mappedBy = "assignee", fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoardTask> boardTasks = new ArrayList<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Builder.Default
    private List<PersonalTask> personalTasks = new ArrayList<>();

    @OneToMany(mappedBy = "invitedUser", fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoardInvitation> invitations = new ArrayList<>();
}
