package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
