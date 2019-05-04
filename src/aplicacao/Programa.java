package aplicacao;

import java.util.*;
import javax.persistence.*;
import dominio.*;

public class Programa {
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		
		Integer id;
		while(true){
			System.out.println("1 para atualizar");
			System.out.println("2 para lista");
			System.out.println("3 para remover");
			System.out.println("4 para pesquisar");
			System.out.println("5 para registra");
			System.out.println("0 para sair");
		
			
			int a = input.nextInt();
			if(a == 0)
				break;
			else if(a == 1) {
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				
				System.out.println("digitar id");
				id = input.nextInt();
				System.out.println("digitar email");
				String email = input.next();
			
				Pessoa pessoaFound = entityManager.find(Pessoa.class,id);
				pessoaFound.setEmail(email);
				entityManager.getTransaction().begin();
				entityManager.persist(pessoaFound);
				entityManager.getTransaction().commit();
				
				entityManager.close();
				entityManagerFactory.close();
				
			}
			else if(a==2) {
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				
				String jpql = "SELECT p FROM Pessoa p";
				List<Pessoa> pessoas = entityManager.createQuery(jpql,Pessoa.class).getResultList();
				System.out.println(pessoas);
				
				entityManager.close();
				entityManagerFactory.close();
			
			}
			else if(a==3) {
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				
				System.out.println("digitar id");
				id = input.nextInt();
				
				Pessoa pessoaFound = entityManager.find(Pessoa.class, id);
				entityManager.getTransaction().begin();
				entityManager.remove(pessoaFound);
				entityManager.getTransaction().commit();
				
				entityManager.close();
				entityManagerFactory.close();
				
			}
			else if(a==4){
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				
				System.out.println("digitar id");
				id = input.nextInt();
				 
				Pessoa pessoaFound = entityManager.find(Pessoa.class, id);
				System.out.println(pessoaFound);
				
			}
			else if(a==5) {
				
				System.out.println("digitar e-mail");
				String email = input.next();
				System.out.println("digitar nome");
				String nome = input.next();
				
				Pessoa pessoa = new Pessoa(null,nome, email);
				
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				
				entityManager.getTransaction().begin();
				entityManager.persist(pessoa);
				entityManager.getTransaction().commit();
				
			}
		}

		input.close();
	}
}