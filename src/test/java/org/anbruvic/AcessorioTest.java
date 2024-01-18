package org.anbruvic;

import org.anbruvic.dao.AcessorioDAO;
import org.anbruvic.dao.IAcessorioDAO;
import org.anbruvic.domain.Acessorio;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class AcessorioTest {
    private IAcessorioDAO acessorioDAO;

    public AcessorioTest() {
        acessorioDAO = new AcessorioDAO();
    }

    @After
    public void end() {
        List<Acessorio> acessorioList = acessorioDAO.buscarTodos();
        acessorioList.forEach(acs -> acessorioDAO.excluir(acs));
    }

    @Test
    public void cadastar() {
        Acessorio aces = criarAcessorio("NU347");
        aces = acessorioDAO.cadastrar(aces);

        assertNotNull(aces);
        assertNotNull(aces.getId());
    }

    private Acessorio criarAcessorio(String codigo) {
        Acessorio aces = new Acessorio();
        aces.setCodigo(codigo);
        aces.setKit("Rodas");
        aces.setValor(600D);


        /*
        Acessorio aces2 = new Acessorio();
        aces2.setCodigo("FR632");
        aces2.setKit("Som");
        aces2.setValor(2200D);
        acessorios.add(aces2);

        Acessorio aces3 = new Acessorio();
        aces3.setCodigo("MH548");
        aces3.setKit("Multimidia");
        aces3.setValor(3500D);
        acessorios.add(aces3);
        */

        return aces;
    }
}
