package br.hendrew.ecommerce.services;

import java.util.List;

import br.hendrew.ecommerce.entity.Produto;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;

public interface ProdutoService {
    
    Produto getProdutoById(long id) throws MenssageNotFoundException;

    List<Produto> getProdutoByNome(String nome) throws MenssageNotFoundException;

    List<Produto> getAllProduto();

    Produto updateProduto(long id, Produto produto) throws MenssageNotFoundException;

    Produto saveProduto(Produto produto);

    void deleteProduto(long id) throws MenssageNotFoundException;

    long countProduto();

    List<Produto> getProdutoPage(int pag, int quant) throws MenssageNotFoundException;

    List<Produto> getPesquisaProduto(String nome) throws MenssageNotFoundException;
    
}
