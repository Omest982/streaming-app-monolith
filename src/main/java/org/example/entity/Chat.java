package org.example.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private boolean isDelayed;
    private boolean isOnlyFollowed;
    private boolean isEnabled;
    @OneToMany
    private List<UserMessage> messages;
}
