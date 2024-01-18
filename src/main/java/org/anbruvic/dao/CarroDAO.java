package org.anbruvic.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.anbruvic.domain.Carro;
import org.anbruvic.domain.Marca;

import java.util.List;

public class CarroDAO implements ICarroDAO{
    @Override
    public List<Carro> cadastrar(List<Carro> car) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        for (Carro carro : car) {
            if (carro.getId() == null) {
                entityManager.persist(carro);
            } else {
                carro = entityManager.merge(carro);
            }
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return car;
    }

    @Override
    public List<Carro> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Carro> query = builder.createQuery(Carro.class);
        Root<Carro> root = query.from(Carro.class);
        query.select(root);

        TypedQuery<Carro> tpQuery = entityManager.createQuery(query);
        List<Carro> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return list;
    }

    @Override
    public void excluir(Carro car) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        car = entityManager.merge(car);
        entityManager.remove(car);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public List<Carro> buscarListaCarrosMarca(String codigoMarca) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c FROM Carro c ");
        sb.append("INNER JOIN c.marca m ");
        sb.append("WHERE m.codigo = :codigoMarca");

        entityManager.getTransaction().begin();
        TypedQuery<Carro> query = entityManager.createQuery(sb.toString(), Carro.class);
        query.setParameter("codigoMarca", codigoMarca);
        List<Carro> carros = query.getResultList();
        entityManager.getTransaction().commit();

        System.out.println("Número de carros encontrados: " + carros.size());

        entityManager.close();
        entityManagerFactory.close();

        return carros;
    }
}
