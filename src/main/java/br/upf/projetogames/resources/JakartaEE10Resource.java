package br.upf.projetogames.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("jakartaee10")
public class JakartaEE10Resource {
    @GET
    public Response ping() {
        return Response.ok("ProjetoGames rodando").build();
    }
}
