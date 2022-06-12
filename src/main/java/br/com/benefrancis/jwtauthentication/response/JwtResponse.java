package br.com.benefrancis.jwtauthentication.response;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import br.com.benefrancis.jwtauthentication.models.Authority;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private Collection<? extends GrantedAuthority> authorities = new ArrayList<Authority>();

	public JwtResponse(String accessToken, Long id, String username, String email,
			Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
