package org.anbruvic;

import org.anbruvic.dao.*;
import org.anbruvic.domain.Acessorio;
import org.anbruvic.domain.Carro;
import org.anbruvic.domain.Marca;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CarroTest {

    private ICarroDAO carroDAO;
    private IMarcaDAO marcaDAO;
    private IAcessorioDAO acessorioDAO;

    public CarroTest() {
        carroDAO = new CarroDAO();
        marcaDAO = new MarcaDAO();
        acessorioDAO = new AcessorioDAO();
    }

    @After
    public void end() {
        List<Carro> carroList = carroDAO.buscarTodos();
        carroList.forEach(car -> carroDAO.excluir(car));

        List<Marca> marcaList = marcaDAO.buscarTodos();
        marcaList.forEach(mar -> marcaDAO.excluir(mar));

        List<Acessorio> acessorioList = acessorioDAO.buscarTodos();
        acessorioList.forEach(acs -> acessorioDAO.excluir(acs));

    }

    @Test
    public void cadastrar() {
        Carro carro = criarCarro("TX",
                criarMarca("M1"),
                criarAcessorio("AC2"),
                "Teste",
                99999D);

        carroDAO.cadastrar(List.of(carro));

        Carro carroBD = carroDAO.buscarTodos().get(0);

        assertNotNull(carroBD);
        assertNotNull(carroBD.getId());
    }

    @Test
    public void consultarCarrosMarca() {
        List<Carro> carros = criarListaCarros();

        Marca marca = carros.get(0).getMarca();

        List<Carro> carrosBD = carroDAO.buscarListaCarrosMarca(String.valueOf(marca));

        assertNotNull(carrosBD);
        //assertEquals(4, carrosBD.size());

        carrosBD.forEach(carro -> {
            assertEquals(carro.getMarca(), marca);
        });
    }



    private Marca criarMarca(String codigo) {
        Marca marcaExistente = marcaDAO.buscarPorCodigoMarca(codigo);
        if (marcaExistente != null) {
            return marcaExistente;
        }

        Marca marca = new Marca();
        marca.setCodigo(codigo);
        marca.setNome("Cupra");
        marca.setQtdConcessionarias(5);
        return marcaDAO.cadastrar(marca);
    }

    private Carro criarCarro(String codigo, Marca marca, Acessorio acessorio, String modelo, Double valor) {
        Carro carros = new Carro();
        carros.setCodigo(codigo);
        carros.setModelo(modelo);
        carros.setMarca(marca);
        carros.setAcessorios(Collections.singletonList(acessorio));
        carros.setValor(valor);

        return carros;
    }

    private List<Carro> criarListaCarros() {
        List<Carro> carroList = new ArrayList<>();

        carroList.add(criarCarro("A1",
                criarMarca("M1"),
                criarAcessorio("AC1"),
                "Formentor",
                29990D
        ));

        carroList.add(criarCarro("B2",
                criarMarca("M1"),
                criarAcessorio("AC2"),
                "Born",
                38500D
        ));

        carroList.add(criarCarro("C3",
                criarMarca("M1"),
                criarAcessorio("AC1"),
                "León",
                34870D
        ));

        carroList.add(criarCarro("D4",
                criarMarca("M1"),
                criarAcessorio("AC2"),
                "Tavascan",
                43200D
        ));

        return carroList;

    }

    private Acessorio criarAcessorio(String codigo) {
        Acessorio aces = new Acessorio();
        aces.setCodigo(codigo);
        aces.setKit("Rodas");
        aces.setValor(600D);
        return aces;
    }

}
