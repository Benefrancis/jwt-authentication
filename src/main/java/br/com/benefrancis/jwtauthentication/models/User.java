package br.com.benefrancis.jwtauthentication.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
// @formatter:off
 @Table(name = "tb_user",
		uniqueConstraints = @UniqueConstraint(
				name = "UK_USERNAME_EMAIL", 
				columnNames = { "USERNAME", "EMAIL" }
				))
 // @formatter:on
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String email;

	private String password;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_perfil", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private List<Perfil> perfis = new ArrayList<Perfil>();
	
	
	
	
	

	Collection<? extends GrantedAuthority> getAuthorities() {

		List<Authority> auth = new ArrayList<Authority>();

		for (Perfil p : perfis) {
			auth.addAll(p.getAuthorities());
		}

		return auth;
	}

}
