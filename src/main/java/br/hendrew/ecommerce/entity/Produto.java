package br.hendrew.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hendrewMartins
 */


@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="produtoID")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = true)
	@Size(max = 100)
    private String nome;

    @NotNull
    @Column(name = "precoVenda", nullable = true)
    private Double precoVenda;

    @NotNull
    @Column(name = "custoProduto", nullable = true)
    private Double custoProduto;

    @NotNull
    @Column(name = "imagem", nullable = true)
	@Size(max = 500)
    private String imagem;

}
