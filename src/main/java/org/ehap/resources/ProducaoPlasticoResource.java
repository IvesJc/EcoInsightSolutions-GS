package org.ehap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ehap.Main;
import org.ehap.models.ProducaoPlastico;
import org.ehap.repositories.ProducaoPlasticoRepository;

import java.util.List;

@Path("producao-plastico")
public class ProducaoPlasticoResource {

    ProducaoPlasticoRepository producaoPlasticoRepository = new ProducaoPlasticoRepository();

    @GET
    public Response getProducaoPlasticos() {
        List<ProducaoPlastico> producaoPlasticos = producaoPlasticoRepository.getProducaoPlasticos();
        
        if (producaoPlasticos.isEmpty()) {
            Main.LOGGER.info("404 - Producao Plastico NOT FOUND");
            return Response.status(404).entity("Producao Plastico not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(producaoPlasticos).build();
    }

    @GET
    @Path("{ano}/{entidade_id}")
    public Response getProducaoPlasticoByUsername(
            @PathParam("ano") int ano,
            @PathParam("entidade_id") int entidadeId
    ){
        ProducaoPlastico producaoPlastico = producaoPlasticoRepository.getProducaoPlasticoByPk(ano, entidadeId);
        if (producaoPlastico == null){
            Main.LOGGER.info("404 - Producao Plastico NOT FOUND");
            return Response.status(404).entity("Producao Plastico não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(producaoPlastico).build();
    }

    @POST
    public Response createProducaoPlastico(ProducaoPlastico producaoPlastico){
        if (producaoPlastico == null){
            Main.LOGGER.info("404 - Producao Plastico can not be null ");
            return Response.status(400).entity("Producao Plastico não pode ser nula").build();
        }
        int result = producaoPlasticoRepository.createProducaoPlastico(producaoPlastico);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Producao Plastico").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(producaoPlastico).build();
    }

    @PUT
    @Path("{ano}/{entidade_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProducaoPlastico(
            @PathParam("ano") int ano,
            @PathParam("entidade_id") int entidadeId,
            ProducaoPlastico producaoPlastico
    ){
        producaoPlastico.setAno(ano);
        producaoPlastico.setEntidadeId(entidadeId);
        ProducaoPlastico pp = producaoPlasticoRepository.getProducaoPlasticoByPk(
                ano, entidadeId);
        if (pp != null){
            producaoPlasticoRepository.updateProducaoPlastico(producaoPlastico);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(producaoPlastico).build();
        }else {
            Main.LOGGER.info("404 - Producao Plastico not found ");
            return Response.status(404).entity("Producao Plastico não registrado").build();
        }
    }

    @DELETE
    @Path("{ano}/{entidade_id}")
    public Response deleteProducaoPlastico(
            @PathParam("ano") int ano,
            @PathParam("entidade_id") int entidadeId
    ){
        ProducaoPlastico pc = producaoPlasticoRepository.getProducaoPlasticoByPk(
                ano, entidadeId);
        if (pc != null){
            producaoPlasticoRepository.deleteByPk(ano, entidadeId);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(pc).build();
        }else {
            return Response.status(400).entity("Producao Plastico não registrado").build();
        }
    }


}
