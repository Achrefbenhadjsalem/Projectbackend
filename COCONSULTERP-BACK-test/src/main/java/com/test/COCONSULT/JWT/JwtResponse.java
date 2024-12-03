package com.test.COCONSULT.JWT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private long id;
    private String token;
    private String type = "access";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private String refreshToken;

    public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.username = username;
        this.authorities = authorities;
    }

    public JwtResponse(String newAccessToken, String newRefreshToken) {
        this.token = newAccessToken;
        this.refreshToken = newRefreshToken;
    }

    public JwtResponse(String s, String s1, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = s;
        this.refreshToken = s1;
        this.username = username;
        this.authorities = authorities;
    }

    public JwtResponse(String s, String s1, String username, Long iduser, Collection<? extends GrantedAuthority> authorities) {
        this.token = s;
        this.refreshToken = s1;
        this.username = username;
        this.id = iduser;
        this.authorities = authorities;
    }


    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

