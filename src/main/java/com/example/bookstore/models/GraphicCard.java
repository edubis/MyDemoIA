package com.example.bookstore.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_graphicard")
@NoArgsConstructor
@Getter
@Setter
public class GraphicCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String marca;

    @Column(nullable = false, length = 30)
    private String memoria;

    @Column(nullable = false, length = 30)
    private String modelo;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 10)
    private Integer anoModelo;

    @Column(nullable = false, length = 100)
    private String sugestao;

    @Column(nullable = false, length = 20)
    private BigDecimal preco;

}
