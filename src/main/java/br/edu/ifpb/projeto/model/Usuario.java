package br.edu.ifpb.projeto.model;

import java.security.MessageDigest;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.xml.bind.DatatypeConverter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario")
@NamedQuery(query = "SELECT u FROM Usuario u WHERE u.email = :email and u.senha = :senha", name = "authorize user")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Column(unique=true)
	private String email;
	private String senha;
	private Boolean coordenador;

	public Usuario() {}

	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
		this.coordenador = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {

		try {
			this.senha = DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(senha.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isCoordenador() {
		return coordenador;
	}

	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}

}
