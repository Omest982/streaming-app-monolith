package org.example.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private User followedBy;
    @ManyToOne
    private User following;
}
