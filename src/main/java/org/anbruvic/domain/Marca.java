package org.anbruvic.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_MARCA")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marc_seq")
    @SequenceGenerator(name = "marc_seq", sequenceName = "sq_marc", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "NOME", length = 20, nullable = false)
    private String nome;

    @Column(name = "QTDCONCESSIONARIAS", length = 5, nullable = false)
    private Integer qtdConcessionarias;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<Carro> carros;

    public Marca() {
        this.carros = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdConcessionarias() {
        return qtdConcessionarias;
    }

    public void setQtdConcessionarias(Integer qtdConcessionarias) {
        this.qtdConcessionarias = qtdConcessionarias;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public void addCarro(List<Carro> car) {
        this.carros.add((Carro) car);
    }


}
