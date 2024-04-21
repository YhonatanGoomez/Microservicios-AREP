package escuelaing.edu.co.microservicios.usuario;



import org.json.JSONObject;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioServicioImpl implements UsuarioServicio{

    private UsuarioRepositorio usuarioRepositorio;


    @Inject
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }


    @Override
    public Usuario agregarUsuario(Usuario usuario) throws Exception {
        return usuarioRepositorio.agregarUsuario(usuario);
    }

    @Override
    public Usuario consultarUsuarioPorId(String usuario) throws Exception {
        return usuarioRepositorio.consultarUsuarioPorId(usuario);
    }

    @Override
    public String login(String login) throws Exception {
        JSONObject objetoJson = new JSONObject(login);
        String usuario = objetoJson.getString("username");
        String contrasena = objetoJson.getString("password");
        Usuario usuarioRepo = usuarioRepositorio.login(usuario, contrasena);
        return GenerateToken.createJWT(usuarioRepo, 3600);
    }
}
