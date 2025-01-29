package com.url.shortener.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.url.shortener.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            Collections.singletonList(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        try {
            return authorities;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'"+e);
        }
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        try {
            return this.password;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getPassword'"+e);
        }
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        try {
            return this.username;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getUsername'"+e);
        }
    }

}
