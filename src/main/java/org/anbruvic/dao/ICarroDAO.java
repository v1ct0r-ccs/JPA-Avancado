package org.anbruvic.dao;

import org.anbruvic.domain.Carro;

import java.util.List;

public interface ICarroDAO {
    List<Carro> cadastrar(List<Carro> car);

    List<Carro> buscarTodos();

    void excluir(Carro car);

    List<Carro> buscarListaCarrosMarca(String codigoMarca);
}
