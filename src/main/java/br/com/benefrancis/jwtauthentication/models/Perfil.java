package br.com.benefrancis.jwtauthentication.models;

import java.util.ArrayList;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
// @formatter:off
 @Table(name = "tb_perfil",
		uniqueConstraints = @UniqueConstraint(
				name = "UK_NOME", 
				columnNames = { "NOME"  }
				))
 // @formatter:on
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_perfil_authority", joinColumns = @JoinColumn(name = "perfil_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private List<Authority> authorities = new ArrayList<Authority>();
}
