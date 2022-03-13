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

@Entity
@Table(name="produtomaterial")
public class ProdutoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="produtomaterialID")
    private Long id;  

    @ManyToOne
    @NotNull
	@JoinColumn(name = "produtoID")
    private Produto produto;

    @ManyToOne
    @NotNull
	@JoinColumn(name = "materialID")
    private Material material;
    
}
