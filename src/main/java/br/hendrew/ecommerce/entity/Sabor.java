package br.hendrew.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="sabor")
public class Sabor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="saborId")
    private Long id;    

    @NotNull
    @Column(name = "descricao", nullable = true)
	@Size(max = 100)
    private String descricao;
   
    @ManyToOne
    @NotNull
	@JoinColumn(name = "produtoID")
    private Produto produto;
}
