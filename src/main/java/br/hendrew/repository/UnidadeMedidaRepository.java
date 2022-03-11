package br.hendrew.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.entity.UnidadeMedida;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UnidadeMedidaRepository implements PanacheRepository<UnidadeMedida>{
    
}
