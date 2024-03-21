package org.example.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class UserStream {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToMany
    private List<User> viewers;
    @OneToOne
    private Chat chat;
    private String previewUrl;
    private String name;
}
