package escuelaing.edu.co.microservicios.usuario;

import java.util.HashMap;
import java.util.Map;

public class LoginDAO {

    private Map<String,String> logins;
    private Encriptacion encriptacion;

    public LoginDAO() {
        logins = new HashMap<>();
        encriptacion = new Encriptacion();
        logins.put("YhonatanGomez", encriptacion.hashString("Yhonatan2000"));
    }

    public boolean existeUsuario(String usuario, String contrasena){
        if(logins.containsKey(usuario)){
            return logins.get(usuario).equals(encriptacion.hashString(contrasena));
        }
        return false;

    }
}
