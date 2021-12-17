package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Getter
public class UserAuthInfo extends BaseDTO implements UserDetails {
    private final String account;
    private final String password;
    private Collection<? extends GrantedAuthority> authorities;
    private final HashMap<String,Object> extraInfo = new HashMap<>();

    public UserAuthInfo(String account, String password){
        this.account = account;
        this.password = password;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.account;
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

    public void setRoles(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for (String role : roles) {
            Assert.isTrue(!role.startsWith("ROLE_"),
                    () -> role + " cannot start with ROLE_ (it is automatically added)");
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        this.authorities = authorities;
    }

    public void setExtraInfo(String key,Object value) {
        this.extraInfo.put(key,value);

    }
}
