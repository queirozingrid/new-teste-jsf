package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.ClienteDao;
import domain.Cliente;
import util.JsfUtil;

@ManagedBean(name = "clientes")
@ViewScoped
public class ClienteBean {
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	private ListDataModel<Cliente> itens;


	public ListDataModel<Cliente> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Cliente> itens) {
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
		List<Cliente> itens2 = ClienteDao.visualizar();
		itens = new ListDataModel<Cliente>(itens2);

	}
	public String atualizar() {
		Cliente cliente = new Cliente();
		cliente.setNome(cliente.getNome());
		cliente.setNovoNome(cliente.getNovoNome());
		
		ClienteDao.atualizar(cliente.getNome(), cliente.getNovoNome());
				
		return null;
	}

}
