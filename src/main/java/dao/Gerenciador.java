package dao;

import java.util.List;

import javax.persistence.EntityManager;

import domain.Cliente;
import factory.JpaUtil;

public class Gerenciador {
	private static EntityManager gerenciador;
	
	public static void cadastrar(Cliente cliente) {
		try {
			gerenciador = JpaUtil.getEntityManager();
			gerenciador.getTransaction().begin();
			gerenciador.persist(cliente);
			gerenciador.getTransaction().commit();			
			
		} catch(Exception e) {
			gerenciador.getTransaction().rollback();
		} finally {
			gerenciador.close();
			
		}
		
	}
	public static void consultar(Cliente cliente) {		
		List<Cliente> consulta;
		String jpql = "select c from Cliente c where c.nome=:nome";
		gerenciador = JpaUtil.getEntityManager();
		gerenciador.getTransaction().begin();
		consulta = gerenciador.createQuery(jpql, Cliente.class).setParameter("nome", cliente.getNome()).getResultList();
		
		if(consulta.size()>0) {
			
		}
		
		
	
		
	}
	

}
