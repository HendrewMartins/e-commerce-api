package br.hendrew.ecommerce.converter;

import javax.enterprise.context.RequestScoped;

import br.hendrew.ecommerce.entity.Material;

@RequestScoped
public class Converter {

    public MaterialConverter materialConverter(Material material) {
        MaterialConverter materialConverter = new MaterialConverter();
        materialConverter.setId(material.getId());
        materialConverter.setDescricao(material.getDescricao());
        materialConverter.setQuantidade(material.getQuantidade());
        materialConverter.setUnidadeMedida(material.getUnidadeMedida().getId() + " - "
                + material.getUnidadeMedida().getDescricao()+" - "+material.getUnidadeMedida().getSigla());
        materialConverter.setValor(material.getValor());
        return materialConverter;
    }

}
