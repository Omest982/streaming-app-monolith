package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String message;
    @CreationTimestamp
    private Timestamp time;
    @ManyToOne
    private User user;
}
