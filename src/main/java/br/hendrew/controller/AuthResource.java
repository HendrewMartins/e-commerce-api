package br.hendrew.controller;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import br.hendrew.entity.Token;
import br.hendrew.entity.User;
import br.hendrew.entity.UserLogin;
import br.hendrew.services.UserServices;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("api/user")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final UserServices userServices;

    @Inject
    public AuthResource(UserServices userServices) {
        this.userServices = userServices;
    }

    @POST
    @PermitAll
    @Path("/register")
    @Operation(summary = "Adicionar Usuário", description = "Criar um novo Usuário e persistir no banco")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType =  "application/json", schema = @Schema(implementation =  User.class))))
    public Response register(@Valid User user) {
        userServices.saveUser(user);
        return Response.ok(null).build();
    }
 
    @POST
    @PermitAll
    @Path("/auth")
    @Produces(MediaType.APPLICATION_JSON)
    public Token login(@Valid UserLogin user){
        return userServices.generateJWT(user.getEmail());  
    }

    @POST
    @RolesAllowed("admin")
    @Path("/admin")
    public Response admin(@Context SecurityContext securityContext){
        return Response.ok(null).build();
        // Example admin endpoint
    }
}