package br.hendrew.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Convert;
import javax.transaction.Transactional;

import br.hendrew.ecommerce.converter.Converter;
import br.hendrew.ecommerce.converter.MaterialConverter;
import br.hendrew.ecommerce.entity.Material;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;
import br.hendrew.ecommerce.repository.MaterialRepository;
import br.hendrew.ecommerce.services.MaterialServices;
import io.quarkus.panache.common.Page;

@RequestScoped
public class DefaultMaterialServices implements MaterialServices {

    private final MaterialRepository materialRepository;

    private final Converter converter;
    
    public DefaultMaterialServices(MaterialRepository materialRepository, Converter converter){
        this.materialRepository = materialRepository;
        this.converter = converter;
    }

    @Override
    public Material getMaterialById(long id) throws MenssageNotFoundException {
        return materialRepository.findByIdOptional(id)
        .orElseThrow(() -> new MenssageNotFoundException("There Material doesn't exist"));

    }

    @Override
    public List<Material> getMaterialByDescricao(String descricao) throws MenssageNotFoundException {
        return materialRepository.find("descricao", descricao).list();
    }
    

    @Override
    public List<MaterialConverter> getAllMaterial() {
        List<Material> material = materialRepository.listAll();
        List<MaterialConverter> converter = new ArrayList<MaterialConverter>();

        for(Material item : material){
            converter.add(this.converter.materialConverter(item));
        }

        return converter;
    }

    @Transactional
    @Override
    public Material updateMaterial(long id, Material material) throws MenssageNotFoundException {
        Material update = getMaterialById(id);
        update.setDescricao(material.getDescricao());
        update.setQuantidade(material.getQuantidade());
        update.setUnidadeMedida(material.getUnidadeMedida());
        update.setValor(material.getValor());
        return update;
    }

    @Transactional
    @Override
    public Material saveMaterial(Material material) {
        materialRepository.persistAndFlush(material);
        return material;
    }

    @Transactional
    @Override
    public void deleteMaterial(long id) throws MenssageNotFoundException {
        materialRepository.delete(getMaterialById(id));
        
    }

    @Override
    public long countMaterial() {
        return materialRepository.count();
    }

    @Override
    public List<Material> getMaterialPage(int pag, int quant) throws MenssageNotFoundException {
        return materialRepository.findAll().page(Page.of(pag, quant)).list(); 
    }
    
    
}
