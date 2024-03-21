package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.enums.UserRole;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
    private boolean isLive;

    private String serverUrl;
    private String streamKey;
    private String ingressId;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;
    private String thumbnaiUrl;

    @OneToOne
    private UserStream userStream;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Follow> followers;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Follow> followed;
    @ManyToMany
    private List<User> blockedUsers;
    private String bio;
}
