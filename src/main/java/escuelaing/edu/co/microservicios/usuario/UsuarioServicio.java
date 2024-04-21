package escuelaing.edu.co.microservicios.usuario;



public interface UsuarioServicio {

    Usuario agregarUsuario(Usuario usuario) throws Exception;

    Usuario consultarUsuarioPorId(String usuario) throws Exception;

    String login(String login) throws Exception;
    
}

