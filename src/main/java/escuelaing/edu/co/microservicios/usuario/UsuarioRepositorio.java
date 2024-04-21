package escuelaing.edu.co.microservicios.usuario;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioRepositorio {

    private UsuarioDAO usuarioDAO;
    private LoginDAO loginDAO;

    @Inject
    public UsuarioRepositorio(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.loginDAO = new LoginDAO();
    }

    public Usuario agregarUsuario(Usuario usuario) throws Exception {
        return usuarioDAO.agregarUsuario(usuario);
    }

    public Usuario consultarUsuarioPorId(String usuario) throws Exception {
        return usuarioDAO.consultarUsuarioPorId(usuario);
    }

    public Usuario login(String usuario, String contrasena) throws Exception {
        if(!loginDAO.existeUsuario(usuario, contrasena)){
            throw new Exception("El usuario o contraseña incorrectos");
        } 
        return usuarioDAO.consultarUsuarioPorId(usuario);       

    }

    
}
