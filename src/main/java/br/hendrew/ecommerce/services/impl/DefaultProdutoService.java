package br.hendrew.ecommerce.services.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.hendrew.ecommerce.entity.Produto;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;
import br.hendrew.ecommerce.repository.ProdutoRepository;
import br.hendrew.ecommerce.services.ProdutoService;
import io.quarkus.panache.common.Page;

@RequestScoped
public class DefaultProdutoService implements ProdutoService{
    
    private final ProdutoRepository produtoRepository;

    
    public DefaultProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto getProdutoById(long id) throws MenssageNotFoundException {
        return produtoRepository.findByIdOptional(id)
        .orElseThrow(() -> new MenssageNotFoundException("There Produto doesn't exist"));

    }

    @Override
    public List<Produto> getProdutoByNome(String nome) throws MenssageNotFoundException {
        return produtoRepository.find("nome", nome).list();
    }
    

    @Override
    public List<Produto> getAllProduto() {
        return produtoRepository.listAll();
    }

    @Transactional
    @Override
    public Produto updateProduto(long id, Produto produto) throws MenssageNotFoundException {
        Produto update = getProdutoById(id);
        update.setCustoProduto(produto.getCustoProduto());
        update.setImagem(produto.getImagem());
        update.setNome(produto.getNome());
        update.setPrecoVenda(produto.getPrecoVenda());
        return update;
    }

    @Transactional
    @Override
    public Produto saveProduto(Produto produto) {
        produtoRepository.persistAndFlush(produto);
        return produto;
    }

    @Transactional
    @Override
    public void deleteProduto(long id) throws MenssageNotFoundException {
        produtoRepository.delete(getProdutoById(id));
        
    }

    @Override
    public long countProduto() {
        return produtoRepository.count();
    }

    @Override
    public List<Produto> getProdutoPage(int pag, int quant) throws MenssageNotFoundException {
        return produtoRepository.findAll().page(Page.of(pag, quant)).list(); 
    }

    @Override
    public List<Produto> getPesquisaProduto(String nome) throws MenssageNotFoundException {
        if(nome == ""){
            return produtoRepository.findAll().list();
        }else{
            return produtoRepository.findNome(nome);
        }
         
    }
    
}
