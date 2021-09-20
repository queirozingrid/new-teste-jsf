package dao;

import java.util.List;
import javax.persistence.EntityManager;
import domain.Produto;
import factory.JpaUtil;

public class ProdutoDao {
	private static EntityManager gerenciador;
	
	public static void cadastrar(Produto produto) {
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			gerenciador.persist(produto);
			gerenciador.getTransaction().commit();
			System.out.println("Cadastrado com sucesso!");
			
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
		} finally { 
			gerenciador.close();
			
		}
		
	}
	
	public static List<Produto> visualizar() {
		String jpql = "select p from Produto p";
		List<Produto> consulta;
		
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			consulta = gerenciador.createQuery(jpql, Produto.class).getResultList();
			
			for (Produto produto : consulta) {
				System.out.println(produto.getDescricao());
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
			gerenciador.merge(produto).setDescricao(produto.getDescricao());
			gerenciador.getTransaction().commit();
			ClienteDao.visualizar();
			
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
			e.printStackTrace();
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
			
		} catch (Exception e) {
			gerenciador.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			gerenciador.close();
		}
	}
	
}
