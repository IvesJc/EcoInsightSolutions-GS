package org.ehap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ehap.Main;
import org.ehap.models.Entidade;
import org.ehap.repositories.EntidadeRepository;
import org.ehap.repositories.EntidadeRepository;

import java.util.List;

@Path("entidade")
public class EntidadeResource {

    EntidadeRepository entidadeRepository = new EntidadeRepository();

    @GET
    public Response getEntidades() {
        List<Entidade> entidadeList = entidadeRepository.getEntidades();
        if (entidadeList.isEmpty()) {
            Main.LOGGER.info("404 - Entidade NOT FOUND");
            return Response.status(404).entity("Entidade not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(entidadeList).build();
    }

    @GET
    @Path("{id}")
    public Response getEntidadeById(@PathParam("id") int id){
        Entidade entidade = entidadeRepository.getEntidadeById(id);
        if (entidade == null){
            Main.LOGGER.info("404 - Entidade NOT FOUND");
            return Response.status(404).entity("Entidade não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(entidade).build();
    }

    @POST
    public Response createEntidade(Entidade entidade){
        if (entidade == null){
            Main.LOGGER.info("404 - Entidade can not be null ");
            return Response.status(400).entity("Entidade não pode ser nula").build();
        }
        int result = entidadeRepository.createEntidade(entidade);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Entidade").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(entidade).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEntidade(@PathParam("id") int id, Entidade entidade){
        entidade.setId(id);
        Entidade entity = entidadeRepository.getEntidadeById(id);
        if (entity != null){
            entidadeRepository.updateEntidade(entidade);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(entidade).build();
        }else {
            Main.LOGGER.info("404 - Entidade not found ");
            return Response.status(404).entity("Entidade não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteCliente(@PathParam("id") int id){
        Entidade entity = entidadeRepository.getEntidadeById(id);
        if (entity != null){
            entidadeRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(entity).build();
        }else {
            return Response.status(400).entity("Entidade não registrado").build();
        }
    }


}
