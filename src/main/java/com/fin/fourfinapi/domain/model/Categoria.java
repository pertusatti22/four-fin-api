package com.fin.fourfinapi.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Categoria {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;

    @Column
    private BigDecimal vInicial;

    @Column
    private BigDecimal vFinal;

    @JsonBackReference
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Transacao> transacoesCategoria;
    
    @Column
    public Long getId() {
        return id;
    }
    
}
