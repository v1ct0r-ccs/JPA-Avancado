package org.anbruvic.dao;

import org.anbruvic.domain.Acessorio;

import java.util.List;

public interface IAcessorioDAO {
    Acessorio cadastrar(Acessorio aces);

    List<Acessorio> buscarTodos();

    void excluir(Acessorio acs);
}
