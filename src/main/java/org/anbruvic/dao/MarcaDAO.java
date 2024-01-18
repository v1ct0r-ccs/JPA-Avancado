package org.anbruvic.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.anbruvic.domain.Marca;

import java.util.List;

public class MarcaDAO implements IMarcaDAO{
    @Override
    public Marca cadastrar(Marca marca) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        marca = entityManager.merge(marca);
        entityManager.persist(marca);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return marca;
    }

    @Override
    public Marca buscarPorCodigoMarca(String codigoMarca) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT m FROM Marca m ");
        sb.append("WHERE m.codigo = :codigoMarca ");

        entityManager.getTransaction().begin();
        TypedQuery<Marca> query = entityManager.createQuery(sb.toString(), Marca.class);
        query.setParameter("codigoMarca", codigoMarca);

        List<Marca> resultado = query.getResultList();

        //Marca marca = query.getSingleResult();
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public Marca atualizar(Marca marca) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        marca = entityManager.merge(marca);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return marca;
    }

    @Override
    public List<Marca> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Marca> query = builder.createQuery(Marca.class);
        Root<Marca> root = query.from(Marca.class);
        query.select(root);

        TypedQuery<Marca> tpQuery = entityManager.createQuery(query);
        List<Marca> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return list;
    }

    @Override
    public void excluir(Marca mar) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        mar = entityManager.merge(mar);
        entityManager.remove(mar);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
