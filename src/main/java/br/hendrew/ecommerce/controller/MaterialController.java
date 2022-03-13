package br.hendrew.ecommerce.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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

import br.hendrew.ecommerce.converter.MaterialConverter;
import br.hendrew.ecommerce.dto.MaterialDTO;
import br.hendrew.ecommerce.entity.Material;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;
import br.hendrew.ecommerce.exceptionhandler.ExceptionHandler;
import br.hendrew.ecommerce.services.MaterialServices;

@RequestScoped
@Path("/api/material")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaterialController {

    private final MaterialServices materialServices;

    @Inject
    public MaterialController(MaterialServices materialServices) {
        this.materialServices = materialServices;
    }

    @GET
    @RolesAllowed("ADMIN")
    @Operation(summary = "Listar Material", description = "Lista Material alunos")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Material.class))))
    public List<MaterialConverter> getMaterial() {
        return materialServices.getAllMaterial();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/page/{page}")
    @Operation(summary = "Listar Material", description = "Lista Paginas do Material")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Material.class))))
    public List<Material> getPageMaterial(@PathParam("page") int pagina) throws MenssageNotFoundException {
        return materialServices.getMaterialPage(pagina, 20);
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Pegar Material", description = "Pesquisa por um ID o Material")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Material.class))),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Material getMaterial(@PathParam("id") int id) throws MenssageNotFoundException {
        return materialServices.getMaterialById(id);
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/count")
    @Operation(summary = "Pegar Quantidade dos Material", description = "Quantidade Repository Material")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public long getQuantidade() throws MenssageNotFoundException {
        return materialServices.countMaterial();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/descricao/{descricao}")
    @Operation(summary = "Pegar Material", description = "Pesquisa por uma Descricao do Material")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Material.class))),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<Material> getMaterialDescricao(@PathParam("descricao") String descricao) throws MenssageNotFoundException {
        return materialServices.getMaterialByDescricao(descricao);
    }

    @POST
    @RolesAllowed("ADMIN")
    @Path("/")
    @Operation(summary = "Adicionar Material", description = "Criar um novo Material e persistir no banco")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Material.class))))
    public Material createMaterial(@Valid MaterialDTO materialDTO) {
        return materialServices.saveMaterial(materialDTO.toMaterial());
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Atualizar um Material", description = "Atualizar um Material existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Material.class))),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Material updateMaterial(@PathParam("id") int id, @Valid MaterialDTO materialDTO)
            throws MenssageNotFoundException {
                return materialServices.updateMaterial(id, materialDTO.toMaterial());

         
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Apagar a Material", description = "Apagar um Material pelo ID")
    @APIResponses(value = { @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public boolean deleteMaterial(@PathParam("id") int id) throws MenssageNotFoundException {
        materialServices.deleteMaterial(id);
        return true;
    }

}
