package br.hendrew.ecommerce.services;

import java.util.List;

import br.hendrew.ecommerce.entity.UnidadeMedida;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;

public interface UnidadeMedidaServices {

    UnidadeMedida getUnidadeMedidaById(long id) throws MenssageNotFoundException;

    List<UnidadeMedida> getUnidadeMedidaByDescricao(String descricao) throws MenssageNotFoundException;

    List<UnidadeMedida> getAllUnidadeMedida();

    UnidadeMedida updateUnidadeMedida(long id, UnidadeMedida unidadeMedida) throws MenssageNotFoundException;

    UnidadeMedida saveUnidadeMedida(UnidadeMedida unidadeMedida);

    void deleteUnidadeMedida(long id) throws MenssageNotFoundException;

    long countUnidadeMedida();

    List<UnidadeMedida> getUnidadeMedidaPage(int pag, int quant) throws MenssageNotFoundException;
    
}
