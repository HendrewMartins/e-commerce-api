package br.hendrew.ecommerce.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.ecommerce.entity.UnidadeMedida;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UnidadeMedidaRepository implements PanacheRepository<UnidadeMedida>{
    
}
