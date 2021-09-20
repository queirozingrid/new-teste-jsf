package domain;
import java.util.List;

import dao.ClienteDao;
import dao.ProdutoDao;

public class Main {
	public static void main(String[] args) {
		List<Cliente> clientes = ClienteDao.visualizar();
		Cliente clienteTeste = clientes.get(0);
		
//		String descricao = "Novalgina";
//		Long quantidade = 20L;
//		Double preco = 20.80;
//		
//		Produto p1 = new Produto();
//		p1.setDescricao(descricao);
//		p1.setPreco(preco);
//		p1.setQuantidade(quantidade);
//		p1.setCliente(clienteTeste);
//		ProdutoDao.cadastrar(p1);
		
		ProdutoDao.visualizar();

		
	}

}
