package escuelaing.edu.co.microservicios.post;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostRepositorio {

    private PostDAO postDAO;

    @Inject
    public PostRepositorio(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> consultarPosts(){
        return postDAO.consultarPosts();
    }

}
