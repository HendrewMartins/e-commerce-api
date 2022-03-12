package br.hendrew.ecommerce.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.ecommerce.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    
}
