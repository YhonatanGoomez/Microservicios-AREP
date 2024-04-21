package escuelaing.edu.co.microservicios.post;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostServicioImpl implements PostServicio{

    private PostRepositorio postRepositorio;

    @Inject
    public PostServicioImpl(PostRepositorio postRepositorio) {
        this.postRepositorio = postRepositorio;
    }


    @Override
    public List<Post> consultarPosts(){
        return postRepositorio.consultarPosts();
    }


    
}
