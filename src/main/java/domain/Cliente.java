package domain;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.Gerenciador;

@ManagedBean(name = "clientes")
@Entity
@Table(name = "clientes")
public class Cliente {	
	private
	String nome;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String senha;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void cadastrar() {
		Cliente c = new Cliente();
		c.setNome(getNome());
		c.setSenha(getSenha());
		Gerenciador.cadastrar(c);
		
	}
	
}
