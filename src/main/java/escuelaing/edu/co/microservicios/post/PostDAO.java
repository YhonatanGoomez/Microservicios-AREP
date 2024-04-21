package escuelaing.edu.co.microservicios.post;

import java.util.HashMap;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostDAO {

    private HashMap<String, Post> posts;

    public PostDAO() {
        posts = new HashMap<>();
    }

    public List<Post> consultarPosts(){
        return  posts.values().stream().toList();
    }
    
}
