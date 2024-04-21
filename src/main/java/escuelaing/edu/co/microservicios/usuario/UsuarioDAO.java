package escuelaing.edu.co.microservicios.usuario;

import java.util.HashMap;
import java.util.Map;


import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioDAO {

    private Map<String,Usuario> usuarios;

    public UsuarioDAO() {
        usuarios = new HashMap<>();
        usuarios.put("YhonatanGomez", new Usuario("Yhonatan Gomez", "YhonatanGomez"));
    }

    public Usuario agregarUsuario(Usuario usuario) throws Exception {
        if(usuarios.containsKey(usuario.getUsuario()) || usuario.getUsuario() == null){
            throw new Exception("Usuario existente o id es nulo");
        }
        return usuarios.put(usuario.getUsuario(), usuario);
    }

    public Usuario consultarUsuarioPorId(String usuario) throws Exception {
        if (!usuarios.containsKey(usuario)) {
            throw new Exception("Usuario no existente");
        }
        return usuarios.get(usuario);
    }
    
}
