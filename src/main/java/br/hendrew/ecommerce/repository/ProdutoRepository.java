package br.hendrew.ecommerce.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.ecommerce.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto>{
    
    public List<Produto> findNome(String nome) {
        return find("lower(nome) like lower(:nome)",
                Parameters.with("nome", "%" + nome + "%")).list();
    }
}
