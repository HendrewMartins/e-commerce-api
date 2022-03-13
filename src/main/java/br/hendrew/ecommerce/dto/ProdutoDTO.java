package br.hendrew.ecommerce.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.hendrew.ecommerce.entity.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ProdutoDTO", description = "DTO para Criar um novo Produto")
public class ProdutoDTO {
    
    @Schema(title = "nome", required = true)
    private String nome;

    @Schema(title = "precoVenda", required = true)
    private Double precoVenda;

    @Schema(title = "custoProduto", required = true)
    private Double custoProduto;

    @Schema(title = "imagem", required = true)
    private String imagem;


    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(getNome());
        produto.setPrecoVenda(getPrecoVenda());
        produto.setCustoProduto(getCustoProduto());
        produto.setImagem(getImagem());
        return produto;
    }

    
    
}
