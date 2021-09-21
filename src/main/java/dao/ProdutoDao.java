package dao;

import java.util.List;
import javax.persistence.EntityManager;
import domain.Produto;
import factory.JpaUtil;
import util.JsfUtil;

public class ProdutoDao {
	private static EntityManager gerenciador;
	
	public static void cadastrar(Produto produto) {
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			gerenciador.persist(produto);
			gerenciador.getTransaction().commit();
			System.out.println("Cadastrado com sucesso!");
			JsfUtil.mensagemSucesso("Produto cadastrado com sucesso!");
			
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
			JsfUtil.mensagemError(e.getMessage());
		} finally { 
			gerenciador.close();
			
		}
		
	}
	
	public static List<Produto> visualizar() {
		String jpql = "select p from Produto p inner join p.cliente c";
		List<Produto> consulta;
		
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			consulta = gerenciador.createQuery(jpql, Produto.class).getResultList();
			
			for (Produto produto : consulta) {
				System.out.println("***************************************************" +
									"id: " + produto.getCodigo() + "\n" +
									"Descrição: " + produto.getDescricao() + "\n" +
									"Quantidade: " + produto.getQuantidade() + "\n" +
									"Preço: " + produto.getPreco() + "\n" +
									"Cliente: " + produto.getCliente().getNome() + " " +
									"id: " + produto.getCliente().getId());
			}
			return consulta;
			
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
			System.out.println("entrei no exception");
			e.printStackTrace();
			return null;
		} finally {
			gerenciador.close();
		}
	}
	
	public static void atualizar(Produto produto) {
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			Produto p = gerenciador.merge(produto);
			p.setDescricao(produto.getDescricao());
			p.setPreco(produto.getPreco());
			p.setQuantidade(produto.getQuantidade());
			p.setCliente(produto.getCliente());
			JsfUtil.mensagemSucesso("Produto editado com sucesso!");
			gerenciador.getTransaction().commit();
			ProdutoDao.visualizar();
			
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
			e.printStackTrace();
			JsfUtil.mensagemError(e.getMessage());
		} finally {
			gerenciador.close();
		}

	}
	public static void remover(Produto produto) {
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			Produto produto2 = gerenciador.find(Produto.class, produto.getCodigo());
			gerenciador.remove(produto2);
			gerenciador.getTransaction().commit();
			JsfUtil.mensagemSucesso("Produto removido com sucesso!");
			ProdutoDao.visualizar();
			
		} catch (Exception e) {
			gerenciador.getTransaction().rollback();
			e.printStackTrace();
			JsfUtil.mensagemError(e.getMessage());
		} finally {
			gerenciador.close();
		}
	}
	
}
