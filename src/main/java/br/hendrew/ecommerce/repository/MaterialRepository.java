package br.hendrew.ecommerce.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.ecommerce.entity.Material;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MaterialRepository implements PanacheRepository<Material>{
    
}
