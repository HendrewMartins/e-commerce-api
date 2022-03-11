package br.hendrew.services.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.hendrew.entity.UnidadeMedida;
import br.hendrew.exception.MenssageNotFoundException;
import br.hendrew.repository.UnidadeMedidaRepository;
import br.hendrew.services.UnidadeMedidaServices;
import io.quarkus.panache.common.Page;

@RequestScoped
public class DefaultUnidadeMedidaServices implements UnidadeMedidaServices { 

    private final UnidadeMedidaRepository unidadeRepository;

    public DefaultUnidadeMedidaServices(UnidadeMedidaRepository unidadeRepository){
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public UnidadeMedida getUnidadeMedidaById(long id) throws MenssageNotFoundException {
        return unidadeRepository.findByIdOptional(id)
        .orElseThrow(() -> new MenssageNotFoundException("There Material doesn't exist"));

    }

    @Override
    public List<UnidadeMedida> getUnidadeMedidaByDescricao(String descricao) throws MenssageNotFoundException {
        return unidadeRepository.find("descricao", descricao).list();
    }
    

    @Override
    public List<UnidadeMedida> getAllUnidadeMedida() {
        return unidadeRepository.listAll();
    }

    @Transactional
    @Override
    public UnidadeMedida updateUnidadeMedida(long id, UnidadeMedida unidadeMedida) throws MenssageNotFoundException {
        UnidadeMedida update = getUnidadeMedidaById(id);
        update.setDescricao(unidadeMedida.getDescricao());
        update.setSigla(unidadeMedida.getSigla());
        return update;
    }

    @Transactional
    @Override
    public UnidadeMedida saveUnidadeMedida(UnidadeMedida unidadeMedida) {
        unidadeRepository.persistAndFlush(unidadeMedida);
        return unidadeMedida;
    }

    @Transactional
    @Override
    public void deleteUnidadeMedida(long id) throws MenssageNotFoundException {
        unidadeRepository.delete(getUnidadeMedidaById(id));
        
    }

    @Override
    public long countUnidadeMedida() {
        return unidadeRepository.count();
    }

    @Override
    public List<UnidadeMedida> getUnidadeMedidaPage(int pag, int quant) throws MenssageNotFoundException {
        return unidadeRepository.findAll().page(Page.of(pag, quant)).list(); 
    }
}
