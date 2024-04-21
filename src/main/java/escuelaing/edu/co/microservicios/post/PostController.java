package escuelaing.edu.co.microservicios.post;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/post")
@ApplicationScoped
public class PostController {

    private PostServicio postServicio;

    @Inject
    public PostController(PostServicio postServicio) {
        this.postServicio = postServicio;
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPosts(){
        try{
            return Response.status(200).entity(postServicio.consultarPosts()).build();
        }catch(Exception e){
            return Response.status(403).entity(e.getMessage()).build();
        }
    }

    
}
