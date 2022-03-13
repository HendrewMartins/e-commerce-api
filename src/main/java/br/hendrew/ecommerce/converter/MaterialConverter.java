package br.hendrew.ecommerce.converter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialConverter {

    private Long id;    
    private String descricao;
    private Double valor;
    private Double quantidade; 
    private String unidadeMedida;   

}
