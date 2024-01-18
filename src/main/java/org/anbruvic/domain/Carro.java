package org.anbruvic.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CARRO")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    @SequenceGenerator(name = "car_seq", sequenceName = "sq_carro", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "MODELO", length = 20, nullable = false)
    private String modelo;

    @Column(name = "VALOR", length = 10, nullable = false)
    private Double valor;


    @ManyToOne
    @JoinColumn(name = "id_marca_fk", foreignKey = @ForeignKey(name = "fk_marca_carro"), referencedColumnName = "id", nullable = false)
    private Marca marca;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "TB_CARRO_ACESSORIO",
            joinColumns = { @JoinColumn(name = "id_carro_fk") },
            inverseJoinColumns = { @JoinColumn(name = "id_acessorio_fk") }
    )
    private List<Acessorio> acessorios;

    public Carro() {
        this.acessorios = new ArrayList<Acessorio>();
    }

    public List<Acessorio> getAcessorios() {
        return acessorios;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }


}
