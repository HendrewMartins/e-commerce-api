package br.hendrew.services.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.hendrew.entity.Material;
import br.hendrew.exception.MenssageNotFoundException;
import br.hendrew.repository.MaterialRepository;
import br.hendrew.services.MaterialServices;
import io.quarkus.panache.common.Page;

@RequestScoped
public class DefaultMaterialServices implements MaterialServices {

    private final MaterialRepository materialRepository;

    public DefaultMaterialServices(MaterialRepository materialRepository){
        this.materialRepository = materialRepository;
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
    public List<Material> getAllMaterial() {
        return materialRepository.listAll();
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
