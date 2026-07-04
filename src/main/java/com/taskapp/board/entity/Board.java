package com.taskapp.board.entity;

import com.taskapp.board_invitation.entity.BoardInvitation;
import com.taskapp.board_task.entity.BoardTask;
import com.taskapp.user.entity.User;
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
@Table(name = "boards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode( of = "id")
@ToString( exclude = { "owner", "boardMembers", "boardTasks" })
@Builder
public class Board {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "background_color", nullable = false, length = 10)
    private String backgroundColor;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoardMember> boardMembers = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoardTask> boardTasks = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoardInvitation> invitations = new ArrayList<>();
}
