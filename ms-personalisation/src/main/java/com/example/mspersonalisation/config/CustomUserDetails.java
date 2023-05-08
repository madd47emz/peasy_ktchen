package com.example.mspersonalisation.config;


import com.example.mspersonalisation.entity.Login;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Data

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    public CustomUserDetails(Login login) {
        this.username = login.getUsername();
        this.password = login.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
