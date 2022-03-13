package br.hendrew.ecommerce.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.hendrew.ecommerce.dto.ProdutoDTO;
import br.hendrew.ecommerce.entity.Produto;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;
import br.hendrew.ecommerce.exceptionhandler.ExceptionHandler;
import br.hendrew.ecommerce.services.ProdutoService;

@RequestScoped
@Path("/api/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    private final ProdutoService produtoServices;

    @Inject
    public ProdutoController(ProdutoService produtoServices) {
        this.produtoServices = produtoServices;
    }

    @GET
    @RolesAllowed("ADMIN")
    @Operation(summary = "Listar Produto", description = "Lista Produto alunos")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))))
    public List<Produto> getProduto() {
        return produtoServices.getAllProduto();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/page/{page}")
    @Operation(summary = "Listar Produto", description = "Lista Paginas do Produto")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))))
    public List<Produto> getPageProduto(@PathParam("page") int pagina) throws MenssageNotFoundException {
        return produtoServices.getProdutoPage(pagina, 20);
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Pegar Produto", description = "Pesquisa por um ID o Produto")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @APIResponse(responseCode = "404", description = "Produto not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Produto getProduto(@PathParam("id") int id) throws MenssageNotFoundException {
        return produtoServices.getProdutoById(id);
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/count")
    @Operation(summary = "Pegar Quantidade dos Produto", description = "Quantidade Repository Produto")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @APIResponse(responseCode = "404", description = "Produto not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public long getQuantidade() throws MenssageNotFoundException {
        return produtoServices.countProduto();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/nome/{nome}")
    @Operation(summary = "Pegar Produto", description = "Pesquisa por uma Descricao do Produto")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @APIResponse(responseCode = "404", description = "Produto not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<Produto> getProdutoDescricao(@PathParam("nome") String nome) throws MenssageNotFoundException {
        return produtoServices.getProdutoByNome(nome);
    }

    @GET
    @PermitAll
    @Path("/pesquisar/{nome}")
    @Operation(summary = "Pegar Produto", description = "Pesquisa por uma Descricao do Produto")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @APIResponse(responseCode = "404", description = "Produto not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<Produto> getProdutoPesquisar(@PathParam("nome") String nome) throws MenssageNotFoundException {
        return produtoServices.getPesquisaProduto(nome);
    }

    @GET
    @PermitAll
    @Path("/buscar-todos")
    @Operation(summary = "Listar Produto", description = "Lista Produto alunos")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))))
    public List<Produto> getListaProduto() {
        return produtoServices.getAllProduto();
    }

    @POST
    @RolesAllowed("ADMIN")
    @Path("/")
    @Operation(summary = "Adicionar Produto", description = "Criar um novo Produto e persistir no banco")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))))
    public Produto createProduto(@Valid ProdutoDTO produtoDTO) {
        return produtoServices.saveProduto(produtoDTO.toProduto());
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Atualizar um Produto", description = "Atualizar um Produto existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
            @APIResponse(responseCode = "404", description = "Produto not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Produto updateProduto(@PathParam("id") int id, @Valid ProdutoDTO produtoDTO)
            throws MenssageNotFoundException {
                return produtoServices.updateProduto(id, produtoDTO.toProduto());

         
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Apagar a Produto", description = "Apagar um Produto pelo ID")
    @APIResponses(value = { @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description = "Produto not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public boolean deleteProduto(@PathParam("id") int id) throws MenssageNotFoundException {
        produtoServices.deleteProduto(id);
        return true;
    }
    
}
