package org.ehap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ehap.Main;
import org.ehap.models.Usuario;
import org.ehap.repositories.UsuarioRepository;
import org.ehap.service.UsuarioService;


import java.util.List;

@Path("usuario")
public class UsuarioResource {

    UsuarioRepository usuarioRepository = new UsuarioRepository();

    UsuarioService usuarioService = new UsuarioService();

    @GET
    public Response getUsuarios() {
        List<Usuario> usuarioList = usuarioRepository.getUsuarios();
        if (usuarioList.isEmpty()) {
            Main.LOGGER.info("404 - Usuario NOT FOUND");
            return Response.status(404).entity("Usuario not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(usuarioList).build();
    }

    @GET
    @Path("{username}")
    public Response getUsuarioByUsername(@PathParam("username") String username){
        Usuario usuario = usuarioRepository.getUsuarioByUsername(username);
        if (usuario == null){
            Main.LOGGER.info("404 - Usuario NOT FOUND");
            return Response.status(404).entity("Usuario não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(usuario).build();
    }

    @POST
    public Response createUsuario(Usuario usuario){
        if (usuario == null){
            Main.LOGGER.info("404 - Usuario can not be null ");
            return Response.status(400).entity("Usuario não pode ser nula").build();
        }
        int result = usuarioRepository.createUsuario(usuario);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Usuario").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(usuario).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validarUsuario(String login, String password){
        boolean credenciaisValidas = usuarioService.validarLoginSenha(login, password);
        if (credenciaisValidas) {
            return Response.ok("Login realizado com sucesso.").build();
        } else {
            return Response.status(400).entity("Usuário ou senha inválidos.").build();
        }
    }

    @PUT
    @Path("{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("username") String username, Usuario usuario){
        usuario.setNomeUsuario(username);
        Usuario user = usuarioRepository.getUsuarioByUsername(username);
        if (user != null){
            usuarioRepository.updateUsuario(usuario);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(usuario).build();
        }else {
            Main.LOGGER.info("404 - Usuario not found ");
            return Response.status(404).entity("Usuario não registrado").build();
        }
    }

    @DELETE
    @Path("{username}")
    public Response deleteUsuario(@PathParam("username") String username){
        Usuario user = usuarioRepository.getUsuarioByUsername(username);
        if (user != null){
            usuarioRepository.deleteByUsername(username);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(user).build();
        }else {
            return Response.status(400).entity("Usuario não registrado").build();
        }
    }


}
