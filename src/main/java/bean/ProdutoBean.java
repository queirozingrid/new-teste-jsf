package bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.ClienteDao;
import dao.ProdutoDao;
import domain.Cliente;
import domain.Produto;

@ManagedBean(name="produtos")
@ViewScoped
public class ProdutoBean {
	private Produto produto;
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;
	private ArrayList<Cliente> comboCliente;
	
	public ArrayList<Cliente> getComboCliente() {
		return comboCliente;
	}
	public void setComboCliente(ArrayList<Cliente> comboCliente) {
		this.comboCliente = comboCliente;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public ArrayList<Produto> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	@PostConstruct
	public void visualizar() {
		itens = new ArrayList<Produto>(ProdutoDao.visualizar());
	}
	public void prepararCadastro() {
		produto = new Produto();
		comboCliente = new ArrayList<Cliente>(ClienteDao.visualizar());
	}
	public void cadastrar() {
		ProdutoDao.cadastrar(produto);
		visualizar();
	}
	public void remover() {
		ProdutoDao.remover(produto);
		visualizar();
	}
	public void editar() {
		ProdutoDao.atualizar(produto);
		visualizar();
	}
	
}
