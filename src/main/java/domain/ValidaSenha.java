package domain;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="validaSenha")
public class ValidaSenha {
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
