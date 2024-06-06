package org.ehap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ehap.Main;
import org.ehap.models.PoluicaoCidade;
import org.ehap.repositories.PoluicaoCidadeRepository;

import java.util.List;

@Path("poluicao-cidade")
public class PoluicaoCidadeResource {

    PoluicaoCidadeRepository poluicaoCidadeRepository = new PoluicaoCidadeRepository();

    @GET
    public Response getPoluicaoCidades() {
        List<PoluicaoCidade> poluicaoCidades = poluicaoCidadeRepository.getPoluicaoCidades();
        if (poluicaoCidades.isEmpty()) {
            Main.LOGGER.info("404 - Poluicao Cidade NOT FOUND");
            return Response.status(404).entity("Poluicao Cidade not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(poluicaoCidades).build();
    }

    @GET
    @Path("{cidade}/{regiao}/{ano}")
    public Response getPoluicaoCidadeByUsername(
            @PathParam("cidade") String cidade,
            @PathParam("regiao") String regiao,
            @PathParam("ano") int ano
    ){
        PoluicaoCidade poluicaoCidade = poluicaoCidadeRepository.getPoluicaoCidadeByPk(cidade, regiao, ano);
        if (poluicaoCidade == null){
            Main.LOGGER.info("404 - Poluicao Cidade NOT FOUND");
            return Response.status(404).entity("Poluicao Cidade não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(poluicaoCidade).build();
    }

    @POST
    public Response createPoluicaoCidade(PoluicaoCidade poluicaoCidade){
        if (poluicaoCidade == null){
            Main.LOGGER.info("404 - Poluicao Cidade can not be null ");
            return Response.status(400).entity("Poluicao Cidade não pode ser nula").build();
        }
        int result = poluicaoCidadeRepository.createPoluicaoCidade(poluicaoCidade);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Poluicao Cidade").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(poluicaoCidade).build();
    }

    @PUT
    @Path("{cidade}/{regiao}/{ano}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePoluicaoCidade(
            @PathParam("cidade") String cidade,
            @PathParam("regiao") String regiao,
            @PathParam("ano") int ano,
            PoluicaoCidade poluicaoCidade
    ){
        poluicaoCidade.setCidade(cidade);
        poluicaoCidade.setRegiao(regiao);
        poluicaoCidade.setAno(ano);
        PoluicaoCidade pc = poluicaoCidadeRepository.getPoluicaoCidadeByPk(
                cidade, regiao, ano
        );
        if (pc != null){
            poluicaoCidadeRepository.updatePoluicaoCidade(poluicaoCidade);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(poluicaoCidade).build();
        }else {
            Main.LOGGER.info("404 - PoluicaoCidade not found ");
            return Response.status(404).entity("PoluicaoCidade não registrado").build();
        }
    }

    @DELETE
    @Path("{cidade}/{regiao}/{ano}")
    public Response deletePoluicaoCidade(
            @PathParam("cidade") String cidade,
            @PathParam("regiao") String regiao,
            @PathParam("ano") int ano
    ){
        PoluicaoCidade pc = poluicaoCidadeRepository.getPoluicaoCidadeByPk(
                cidade, regiao, ano
        );
        if (pc != null){
            poluicaoCidadeRepository.deleteByPk(cidade, regiao, ano);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(pc).build();
        }else {
            return Response.status(400).entity("Poluicao Cidade não registrado").build();
        }
    }


}
