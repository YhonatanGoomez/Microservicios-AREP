package escuelaing.edu.co.microservicios.usuario;

import java.util.Arrays;
import java.util.HashSet;

import io.smallrye.jwt.build.Jwt;

public class GenerateToken {

    /**
     * Generate JWT token
     */
    public static String createJWT(Usuario usuario, long expirationInSecs) {
        String token =
                Jwt.issuer("https://example.com/issuer")
                        .upn("jdoe@quarkus.io")
                        .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                        .claim("usuario", usuario.getUsuario())
                        .claim("nombre", usuario.getNombre())
                        .sign();
        return token;
    }
}