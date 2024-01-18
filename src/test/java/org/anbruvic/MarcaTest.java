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

import static org.junit.Assert.*;

public class MarcaTest {

    private IMarcaDAO marcaDAO;
    private ICarroDAO carroDAO;
    private IAcessorioDAO acessorioDAO;

    public MarcaTest() {
        marcaDAO = new MarcaDAO();
        carroDAO = new CarroDAO();
        acessorioDAO = new AcessorioDAO();
    }

    @After
    public void end() {
        List<Marca> marcaList = marcaDAO.buscarTodos();
        marcaList.forEach(mar -> marcaDAO.excluir(mar));

        List<Carro> carroList = carroDAO.buscarTodos();
        carroList.forEach(car -> carroDAO.excluir(car));

        List<Acessorio> acessorioList = acessorioDAO.buscarTodos();
        acessorioList.forEach(acs -> acessorioDAO.excluir(acs));
    }

    @Test
    public void cadastrar() {
        Marca marca = criarMarca("M1");

        marca = marcaDAO.cadastrar(marca);

        assertNotNull(marca);
        assertNotNull(marca.getId());
    }

    @Test
    public void buscaPorMarca() {
       Marca marca = criarMarca("M1");
       marca = marcaDAO.cadastrar(marca);

       assertNotNull(marca);
       assertNotNull(marca.getId());

       Marca marcaBD = marcaDAO.buscarPorCodigoMarca(marca.getCodigo());
       assertEquals(marca.getNome(), marcaBD.getNome());
    }

    @Test
    public void listaCarrosMarca() {
        Marca marca = criarMarca("M1");
        List<Carro> carros = criarCarro("ESTQ4");
        marca.setCarros(carros);
        marca = marcaDAO.cadastrar(marca);

        Marca marcaBD = marcaDAO.buscarPorCodigoMarca(marca.getCodigo());
        assertNotNull(marcaBD);
        assertNotNull(marcaBD.getCarros());
    }


    @Test
    public void alterar() {
        Marca marca = criarMarca("M1");
        marca = marcaDAO.cadastrar(marca);

        assertNotNull(marca);
        assertNotNull(marca.getId());

        Marca marcaBD = marcaDAO.buscarPorCodigoMarca(marca.getCodigo());
        assertEquals(marca.getId(), marcaBD.getId());
        marcaBD.setQtdConcessionarias(7);

        Marca marcaBDAtt = marcaDAO.atualizar(marcaBD);
        assertEquals(7, marcaBDAtt.getQtdConcessionarias(), 0);
    }

    private Marca criarMarca(String codigo) {
        Marca marca = new Marca();
        marca.setCodigo(codigo);
        marca.setNome("Cupra");
        marca.setQtdConcessionarias(5);
        return marcaDAO.cadastrar(marca);
    }

    private List<Carro> criarCarro(String codigo) {
        Marca marca = criarMarca(codigo);
        Acessorio aces = criarAcessorio(codigo);
        List<Carro> carros = new ArrayList<>();


        //Carro 1
        Carro car = new Carro();
        car.setCodigo("A1");
        car.setModelo("Formentor");
        car.setValor(29990D);
        car.setMarca(marca);
        car.setAcessorios(Collections.singletonList(aces));
        carros.add(car);


        //Carro 2
        Carro car2 = new Carro();
        car2.setCodigo("B2");
        car2.setModelo("Born");
        car2.setValor(38500D);
        car2.setMarca(marca);
        car2.setAcessorios(Collections.singletonList(aces));
        carros.add(car2);


        //Carro 3
        Carro car3 = new Carro();
        car3.setCodigo("C3");
        car3.setModelo("León");
        car3.setValor(34870D);
        car3.setMarca(marca);
        car3.setAcessorios(Collections.singletonList(aces));
        carros.add(car3);


        //Carro 4
        Carro car4 = new Carro();
        car4.setCodigo("D4");
        car4.setModelo("Tavascan");
        car4.setValor(43200D);
        car4.setMarca(marca);
        car4.setAcessorios(Collections.singletonList(aces));
        carros.add(car4);

        return carros;
    }

    private Acessorio criarAcessorio(String codigo) {
        Acessorio aces = new Acessorio();
        aces.setCodigo(codigo);
        aces.setKit("Rodas");
        aces.setValor(600D);
        return aces;
    }
}
