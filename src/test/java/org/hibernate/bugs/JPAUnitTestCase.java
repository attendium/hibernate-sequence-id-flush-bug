package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		final var parent = new Account();
		entityManager.persist(parent);

		final var user = new Person(parent);
		parent.addChild(user);

		final var cb = entityManager.getCriteriaBuilder();
		final var cq = cb.createQuery(Long.class);
		final var ticket = cq.from(Ticket.class);
		cq.select(cb.count(ticket)).where(ticket.get("owner").in(List.of(user)));
		entityManager.createQuery(cq).getSingleResult().intValue();

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
