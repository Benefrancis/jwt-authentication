package br.com.benefrancis.jwtauthentication.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.benefrancis.jwtauthentication.models.Authority;
import br.com.benefrancis.jwtauthentication.models.Perfil;
import br.com.benefrancis.jwtauthentication.models.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private List<Perfil> perfis = new ArrayList<Perfil>();

	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<Authority> auth = new ArrayList<Authority>();

		for (Perfil p : perfis) {
			auth.addAll(p.getAuthorities());
		}

		return auth;
	}

	public UserDetailsImpl(Long id, String username, String email, String password, List<Perfil> perfis) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.perfis = perfis;

	}

	public static UserDetailsImpl build(User user) {

		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getPerfis());
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}
