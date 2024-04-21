package escuelaing.edu.co.microservicios.hilo;

import java.util.Collection;

import escuelaing.edu.co.microservicios.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Hilo {

    private String id;
    private String usuarioCreador;
    private Collection<Post> posts;

    public void agregarPost(Post post) {
        this.posts.add(post);
    }
    
}
