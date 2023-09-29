package com.entity;

import com.enums.Gender;
import com.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "id", initialValue = 100, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "email")
    private String email;

    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "userstatus")
    private UserStatus userstatus;

    private String roles;


    public User(User user) {
        id = user.getId();
        firstname = user.getFirstname();
        lastname = user.getLastname();
        gender = user.getGender();
        email = user.getEmail();
        password = user.getPassword();
        mobile = user.getMobile();
        userstatus = user.getUserstatus();
        roles = user.getRoles();
    }

    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
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