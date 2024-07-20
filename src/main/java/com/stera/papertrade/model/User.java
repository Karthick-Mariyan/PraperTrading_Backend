package com.stera.papertrade.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.Nullable;

import jakarta.annotation.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document("User")
@Data
public class User implements UserDetails {
    @Id 
    @Nullable
    private String userId;
    private String userName;
    private String mail;
    private String phone;
    private String password;
    private ArrayList<Integer> tradeId;
    private ArrayList<Integer> watchListId;
    private Role role;

    public User(String userId, String userName, String mail, String phone, String password, ArrayList<Integer> tradeId,
            ArrayList<Integer> watchListId) {
        this.userId = userId;
        this.userName = userName;
        this.mail = mail;
        this.phone = phone;
        this.password = password;
        this.tradeId = tradeId;
        this.watchListId = watchListId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {return this.userName; }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
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
