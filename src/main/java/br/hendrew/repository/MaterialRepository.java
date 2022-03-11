package br.hendrew.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.entity.Material;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MaterialRepository implements PanacheRepository<Material>{
    
}
