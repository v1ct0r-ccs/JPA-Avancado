package org.anbruvic.dao;


import org.anbruvic.domain.Marca;

import java.util.List;

public interface IMarcaDAO {

    Marca cadastrar(Marca marca);

    Marca buscarPorCodigoMarca(String codigoMarca);

    Marca atualizar(Marca marca);

    List<Marca> buscarTodos();

    void excluir(Marca mar);
}
