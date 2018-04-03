package br.usjt.arqsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 *  @author Vin�cius Neves Pereira - R.A.: 201511080 - Turma:SIN3AN-MCA
 */

@Entity
@Table
public class Usuario implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue
	@Column(name="id_usuario")
		private int id;
	
	@NotNull
	@Size(min = 5, max=50, message="O nome de usuario deve estar entre 5 e 50 caracteres.")
	@Column
		private String username;
	
	
	@NotNull
	@Size(min = 5, max=50, message="A senha deve estar entre 5 e 50 caracteres.")
	@Column
		private String password;
	
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}

}
