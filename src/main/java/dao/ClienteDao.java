package dao;

import java.util.List;

import javax.persistence.EntityManager;

import domain.Cliente;
import factory.JpaUtil;
import util.JsfUtil;


public class ClienteDao {
	private static EntityManager gerenciador;
	
	public static void cadastrar(Cliente cliente) {
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			gerenciador.persist(cliente);
			gerenciador.getTransaction().commit();
			System.out.println("Cadastrado com sucesso!");
			JsfUtil.mensagemSucesso("Cliente cadastrado com sucesso!");
			visualizar();
			
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
			JsfUtil.mensagemError(e.getMessage());

		} finally { 
			gerenciador.close();
			
		}
		
	}
	public static List<Cliente> visualizar() {
		String jpql = "select c from Cliente c";
		List<Cliente> consulta;
		
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			consulta = gerenciador.createQuery(jpql, Cliente.class).getResultList();
			
			for (Cliente cliente : consulta) {
				System.out.println(cliente.getNome());
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
	public static Cliente consultaPorId(Long id) {
			
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			Cliente cliente = gerenciador.find(Cliente.class, id);
			System.out.println(cliente.getNome());
			return(cliente);
			
		} catch (Exception e) {
			gerenciador.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			gerenciador.close();
		}	
	}
	
	public static List<Cliente> consultaPorNome(String nome) {
		String jpql = "select c from Cliente c where lower(c.nome) like lower(concat('%', :nome, '%'))";
		List<Cliente> consulta;
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			consulta = gerenciador.createQuery(jpql, Cliente.class).setParameter("nome", nome).getResultList();
			
			for (Cliente cliente : consulta) {
				System.out.println("ID: " + cliente.getId() + " Nome: " + cliente.getNome());
			}
			return consulta;
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			gerenciador.clear();
			
			
		}
	}
	public static void atualizar(Cliente cliente) {
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			gerenciador.merge(cliente).setNome(cliente.getNome());
			gerenciador.getTransaction().commit();
			JsfUtil.mensagemSucesso("Cliente editado com sucesso!");
			ClienteDao.visualizar();
			
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
			e.printStackTrace();
			JsfUtil.mensagemError(e.getMessage());
		} finally {
			gerenciador.close();
	}

}
	public static void remover(Cliente cliente) {
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			Cliente cliente2 = gerenciador.find(Cliente.class, cliente.getId());
			gerenciador.remove(cliente2);
			gerenciador.getTransaction().commit();
			JsfUtil.mensagemSucesso("Cliente removido com sucesso!");
			ClienteDao.visualizar();
			
		} catch (Exception e) {
			gerenciador.getTransaction().rollback();
			e.printStackTrace();
			JsfUtil.mensagemError(e.getMessage());
		} finally {
			gerenciador.close();
		}
	}
}