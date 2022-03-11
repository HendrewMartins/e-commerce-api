package br.hendrew.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    
}
