package br.hendrew.ecommerce.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.ecommerce.entity.UnidadeMedida;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class UnidadeMedidaRepository implements PanacheRepository<UnidadeMedida> {

    public List<UnidadeMedida> findDescricao(String descricao) {
        return find("lower(descricao) like lower(:descricao)",
                Parameters.with("descricao", "%" + descricao + "%")).list();
    }
}
