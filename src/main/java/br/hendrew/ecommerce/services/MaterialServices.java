package br.hendrew.ecommerce.services;

import java.util.List;

import br.hendrew.ecommerce.converter.MaterialConverter;
import br.hendrew.ecommerce.entity.Material;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;

public interface MaterialServices {
    Material getMaterialById(long id) throws MenssageNotFoundException;

    List<Material> getMaterialByDescricao(String descricao) throws MenssageNotFoundException;

    List<MaterialConverter> getAllMaterial();

    Material updateMaterial(long id, Material material) throws MenssageNotFoundException;

    Material saveMaterial(Material material);

    void deleteMaterial(long id) throws MenssageNotFoundException;

    long countMaterial();

    List<Material> getMaterialPage(int pag, int quant) throws MenssageNotFoundException;
}
