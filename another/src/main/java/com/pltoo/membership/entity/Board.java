package com.pltoo.membership.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "post")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardnum")
    private Integer boardNum;

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "boardtitle", nullable = false)
    private String boardTitle;

    @Column(name = "boardcontent", nullable = false)
    private String boardContent;

    @Column(name = "viewcount")
    private int viewCount = 0;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    @Column(name = "likes")
    private int likes = 0;

    @Column(name = "dislikes")
    private int dislikes = 0;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filepath;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
