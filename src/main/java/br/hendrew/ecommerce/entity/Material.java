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

/**
 * @author hendrewMartins
 */


@Getter
@Setter
@Entity
@Table(name="material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="materialID")
    private Long id;    

    @NotNull
    @Column(name = "descricao", nullable = true)
	@Size(max = 100)
    private String descricao;
    
    @NotNull
    @Column(name = "valor", nullable = true)
    private Double valor;

    @NotNull
    @Column(name = "quantidade", nullable = true)
    private Double quantidade; 

    @ManyToOne
    @NotNull
	@JoinColumn(name = "unidadeid")
    private UnidadeMedida unidadeMedida;
    
}
