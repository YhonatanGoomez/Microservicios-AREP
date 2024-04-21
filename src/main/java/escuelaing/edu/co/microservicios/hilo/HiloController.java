package escuelaing.edu.co.microservicios.hilo;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

import escuelaing.edu.co.microservicios.post.Post;

@Path("/hilo")
@ApplicationScoped
public class HiloController {

    @Inject
    JsonWebToken jwt;

    private HiloServicio hiloServicio;

    @Inject
    public HiloController(HiloServicio hiloServicio) {
        this.hiloServicio = hiloServicio;
    }

    @POST
    @Path("")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarHilo(@Context SecurityContext ctx, Hilo hilo) {
        try {
            verifyJWT(ctx);
            return Response.status(200).entity(hiloServicio.agregarHilo(hilo)).build();
        } catch (Exception e) {
            return Response.status(403).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/hilos")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarHilos(@Context SecurityContext ctx){
        try{
            verifyJWT(ctx);
            return Response.status(200).entity(hiloServicio.consultarHilos()).build();
        }catch(Exception e){
            return Response.status(403).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarHiloPorId(@Context SecurityContext ctx, @PathParam("id") String id){
        try{
            verifyJWT(ctx);
            return Response.status(200).entity(hiloServicio.consultarHiloPorId(id)).build();
        }catch(Exception e){
            return Response.status(403).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/post")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarPostAlHilo(@Context SecurityContext ctx, @PathParam("id") String id, Post post) {
        try {
            verifyJWT(ctx);
            hiloServicio.agregarPostAlHilo(id, post);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(403).entity(e.getMessage()).build();
        }

    }

    private void verifyJWT(SecurityContext ctx) {
        if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else if (jwt.getClaimNames() == null) {
            throw new InternalServerErrorException("Without JWT");
        } else if (ctx.getUserPrincipal() == null) {
            throw new InternalServerErrorException("Anonymous");
        }
    }

}