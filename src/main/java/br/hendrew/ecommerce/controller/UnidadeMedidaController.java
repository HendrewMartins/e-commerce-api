package br.hendrew.ecommerce.controller;


import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.hendrew.ecommerce.dto.UnidadeMedidaDTO;
import br.hendrew.ecommerce.entity.UnidadeMedida;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;
import br.hendrew.ecommerce.exceptionhandler.ExceptionHandler;
import br.hendrew.ecommerce.services.UnidadeMedidaServices;

@RequestScoped
@Path("/api/unidade-medida")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnidadeMedidaController {

    private final UnidadeMedidaServices unidadeMedidaServices;

    @Inject
    public UnidadeMedidaController(UnidadeMedidaServices unidadeMedidaServices) {
        this.unidadeMedidaServices = unidadeMedidaServices;
    }

    @GET
    @PermitAll
    @Operation(summary = "Listar UnidadeMedida", description = "Lista UnidadeMedida")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeMedida.class))))
    public List<UnidadeMedida> getUnidadeMedida() {
        return unidadeMedidaServices.getAllUnidadeMedida();
    }

    @GET
    @PermitAll
    @Path("/page/{page}")
    @Operation(summary = "Listar UnidadeMedida", description = "Lista Paginas do UnidadeMedida")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeMedida.class))))
    public List<UnidadeMedida> getPageUnidadeMedida(@PathParam("page") int pagina) throws MenssageNotFoundException {
        return unidadeMedidaServices.getUnidadeMedidaPage(pagina, 20);
    }

    @GET
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Pegar UnidadeMedida", description = "Pesquisa por um ID o UnidadeMedida")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeMedida.class))),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public UnidadeMedida getUnidadeMedida(@PathParam("id") int id) throws MenssageNotFoundException {
        return unidadeMedidaServices.getUnidadeMedidaById(id);
    }

    @GET
    @PermitAll
    @Path("/count")
    @Operation(summary = "Pegar Quantidade dos UnidadeMedida", description = "Quantidade Repository UnidadeMedida")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public long getQuantidade() throws MenssageNotFoundException {
        return unidadeMedidaServices.countUnidadeMedida();
    }

    @GET
    @PermitAll
    @Path("/descricao/{descricao}")
    @Operation(summary = "Pegar UnidadeMedida", description = "Pesquisa por uma Descricao do UnidadeMedida")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeMedida.class))),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<UnidadeMedida> getUnidadeMedidaDescricao(@PathParam("descricao") String descricao) throws MenssageNotFoundException {
        return unidadeMedidaServices.getUnidadeMedidaByDescricao(descricao);
    }

    @POST
    @PermitAll
    @Path("/")
    @Operation(summary = "Adicionar UnidadeMedida", description = "Criar um novo UnidadeMedida e persistir no banco")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeMedida.class))))
    public UnidadeMedida createUnidadeMedida(@Valid UnidadeMedidaDTO materialDTO) {
        return unidadeMedidaServices.saveUnidadeMedida(materialDTO.toUnidade());
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Atualizar um UnidadeMedida", description = "Atualizar um UnidadeMedida existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeMedida.class))),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public UnidadeMedida updateMaterial(@PathParam("id") int id, @Valid UnidadeMedidaDTO materialDTO)
            throws MenssageNotFoundException {
                return unidadeMedidaServices.updateUnidadeMedida(id, materialDTO.toUnidade());

         
    }

    @DELETE
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Apagar a UnidadeMedida", description = "Apagar um UnidadeMedida pelo ID")
    @APIResponses(value = { @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public boolean deleteUnidadeMedida(@PathParam("id") int id) throws MenssageNotFoundException {
        unidadeMedidaServices.deleteUnidadeMedida(id);
        return true;
    }

}
