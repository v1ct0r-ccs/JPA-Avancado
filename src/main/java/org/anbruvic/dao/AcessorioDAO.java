package org.anbruvic.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.anbruvic.domain.Acessorio;
import java.util.List;

public class AcessorioDAO implements IAcessorioDAO{
    @Override
    public Acessorio cadastrar(Acessorio aces) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        aces = entityManager.merge(aces);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return aces;
    }

    @Override
    public List<Acessorio> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Acessorio> query = builder.createQuery(Acessorio.class);
        Root<Acessorio> root = query.from(Acessorio.class);
        query.select(root);

        TypedQuery<Acessorio> tpQuery = entityManager.createQuery(query);
        List<Acessorio> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return list;
    }

    @Override
    public void excluir(Acessorio acs) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        acs = entityManager.merge(acs);
        entityManager.remove(acs);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
