package com.dp.dpshopbackend.message.response;

import com.dp.dpshopbackend.dto.UtilisateurPOSTDto;

import java.util.List;

public class JwtResponse extends UtilisateurPOSTDto {

    private final Long id;
    private List<String> roles;
    //  private final Collection<? extends GrantedAuthority> authorities;
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;

    public JwtResponse(String accessToken, Long id, String username, String email,
                       List<String> roles) {

        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String accessToken, Long id, String username, String email) {

        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}