package org.codeforpizza.registrationservice.models.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Role is used to represent a role in the database
 * It implements GrantedAuthority which is used by Spring Security to authenticate roles for users.
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="role_id")
    private Integer roleId;

    private String authority;


    public Role(String authority){
        this.authority = authority;
    }




}
