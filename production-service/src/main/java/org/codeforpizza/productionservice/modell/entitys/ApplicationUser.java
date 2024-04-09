package org.codeforpizza.productionservice.modell.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * ApplicationUser is used to represent a user in the database
 * It implements UserDetails which is used by Spring Security to authenticate users.
 * it has a few unused methods that are required by the UserDetails interface, some day i might implement them
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class ApplicationUser implements UserDetails{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;
    @Column(unique=true)
    private String username;
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_role_junction",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Production> productions = new ArrayList<>();

    public ApplicationUser(String username, String password, Set<Role> authorities, List<Production> productions) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.productions = productions;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
