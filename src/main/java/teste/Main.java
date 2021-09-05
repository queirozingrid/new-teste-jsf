package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("teste");
	public static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	public static void main(String [] args) {
//			entityManager.getTransaction().begin();
//			entityManager.remove(entityManager.find(Cliente.class, 1));
//			entityManager.getTransaction().commit();
//			entityManager.close();
	
	}

}