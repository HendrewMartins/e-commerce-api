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
@Table(name="unidademedida")
public class UnidadeMedida {
    
    @Id 
    @Column(name="unidadeid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="descricao")
    @Size(max=30)
    private String descricao;

    @NotNull
    @Column(name="sigla")
    @Size(max=10)
    private String sigla;

}
