package bean;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dao.ClienteDao;
import domain.Cliente;
import util.JsfUtil;

@ManagedBean(name = "clientes")
@ViewScoped
public class ClienteBean {
	private Cliente cliente;
	private ArrayList<Cliente> itens;
	private ArrayList<Cliente> itensFiltrados;
	
	public ArrayList<Cliente> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Cliente> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public ArrayList<Cliente> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Cliente> itens) {
		this.itens = itens;
	}
	
	public void prepararCadastro() {
		cliente = new Cliente();
	}
	
	public void cadastrar() {
		/* cliente.setNome(cliente.getNome()); */
		try {
			ClienteDao.cadastrar(cliente);
			visualizar();
			JsfUtil.mensagemSucesso("Cliente cadastrado com sucesso!");
			
		} catch(Exception e) {
			e.printStackTrace();
			JsfUtil.mensagemError(e.getMessage());
		}

	} 
	
	@PostConstruct
	public void visualizar() {
		itens = new ArrayList<Cliente>(ClienteDao.visualizar());
	}
	public void atualizar() {
		try {
			ClienteDao.atualizar(cliente);
			visualizar();
			JsfUtil.mensagemSucesso("Cliente editado com sucesso!");
		} catch(Exception e) {
			e.printStackTrace();
			JsfUtil.mensagemError(e.getMessage());
		}
		

	}
	
	public void remover() {
		try {
			ClienteDao.remover(cliente);
			visualizar();
			JsfUtil.mensagemSucesso("Cliente removido com sucesso!");
			
		} catch(Exception e) {
			e.printStackTrace();
			JsfUtil.mensagemError(e.getMessage());
		}

	}

}
