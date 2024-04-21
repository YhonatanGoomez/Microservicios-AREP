package escuelaing.edu.co.microservicios.usuario;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/usuario")
@ApplicationScoped
public class UsuarioController {

    @Inject
    JsonWebToken jwt;

    private UsuarioServicio usuarioServicio;

    @Inject
    public UsuarioController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @POST
    @Path("")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarUsuario(Usuario usuario) {
        try {
            return Response.status(200).entity(usuarioServicio.agregarUsuario(usuario)).build();
        } catch (Exception e) {
            return Response.status(403).entity(e.getMessage()).build();
        }
        
    }

    @GET
    @Path("/{usuario}")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuarioPorId(@Context SecurityContext ctx, String usuario){
        try{
            if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
                throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
            } else if (jwt.getClaimNames() == null) {
                throw new InternalServerErrorException("Without JWT");
            } else if (ctx.getUserPrincipal() == null) {
                throw new InternalServerErrorException("Anonymous");
            }
            return Response.status(200).entity(usuarioServicio.consultarUsuarioPorId(usuario)).build();
        }catch(Exception e){
            return Response.status(403).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String login){
        try{
            String token = usuarioServicio.login(login);
            return Response.status(200).entity("{\"jwt\":\"" + token  + "\"}").build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(403).entity(e.getMessage()).build();
        }
    }


    
}
