# JPA Avançado

## JPQL - Java Persistence Query Language

É uma linguagem muito parecida com o SQL tradicional, só que orientado a objeto.

- Exemplo
```
@Override
    public Matricula buscarPorCurso(Curso curso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT m FROM Matricula m ");
        sb.append("INNER JOIN Curso c on c = m.curso ");
        sb.append("WHERE c = :curso");

        entityManager.getTransaction().begin();
        TypedQuery<Matricula> query =
                entityManager.createQuery(sb.toString(), Matricula.class);
        query.setParameter("curso", curso);
        Matricula matricula = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return matricula;
    }
```

- Query gerada
```
Hibernate:
    select
        matricula0_.id as id1_1_,
        matricula0_.CODIGO as codigo2_1_,
        matricula0_.id_cursofk as id_curso6_1_,
        matricula0_.status as status4_1_,
        matricula0_.valor as valor5_1_
    from
        TB_MATRICULA matricula0_
    inner join
        TB_CURSO curso1_
            on (
                curso1.id=matricula0_.id_curso_fk
            )
    where
        curso1_.CODIGO=?
Hibernate:
    select
        curso0_.id as id1_0_0_,
        curso0_.CODIGO as codigo2_0_0_,
        curso0_.DESCRICAO as descrica3_0_0_,
        curso0_.NOME as nome4_0_0_
        
    from
        TB_CURSO curso0_
    where
        curso0_.id=?
```

## Criteria

A JPA Criteria API é uma biblioteca que noa auxilia como uma forma alternativa de definir queries JPA. Ela é muito util para queries dinâmicas já que a sua estrutura exata é conhecida apenas em tempo de execução. Uma das maiores vantagens de usar a API consiste na detecção de erros previamente, ou seja, antes da execução. E sua grande desvantagem é a dificuldade de implementação. Logo, teremos que escrever mais código para executar a mesma operação se comparada a JPQL.

- Exemplo
```
@Override
public Matricula buscarPorCodigoCursoCriteria(String codigoCurso) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Matricula> query = builder.createQuery(Matricula.class);
    Root<Matricula> root = query.from(Matricula.class);
    Join<Object, Object> join = root.join("curso", JoinType.INNER);
    query.select(root).where(builder.equal(join.get("codigo"), codigoCurso));

    TypedQuery<Matricula> tpQuery =
            entityManager.createQuery(query);
    Matricula matricula = tpQuery.getSingleResult();

    entityManager.close();
    entityManagerFactory.close();

    return matricula;
}
```

