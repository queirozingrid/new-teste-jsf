package bean;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dao.ClienteDao;
import domain.Cliente;

@ManagedBean(name = "clientes")
@ViewScoped
public class ClienteBean {
    private Cliente cliente;
    private String nomeBusca;
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


    public String getNomeBusca() {
		return nomeBusca;
	}

	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
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
        ClienteDao.cadastrar(cliente);

    } 

    @PostConstruct
    public void visualizar() {
        itens = new ArrayList<Cliente>(ClienteDao.visualizar());
    }
    public void atualizar() {
        ClienteDao.atualizar(cliente);
    }

    public void remover() {
        ClienteDao.remover(cliente);
    }
    public void pesquisar() {
        itens = new ArrayList<Cliente>(ClienteDao.consultaPorNome(getNomeBusca()));

    }
}