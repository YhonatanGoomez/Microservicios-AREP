package escuelaing.edu.co.microservicios.hilo;

import java.util.List;

import escuelaing.edu.co.microservicios.post.Post;

public interface HiloServicio {

    Hilo agregarHilo(Hilo hilo) throws Exception;

    List<Hilo> consultarHilos();

    Hilo consultarHiloPorId(String id) throws Exception;

    void agregarPostAlHilo(String id, Post post) throws Exception;
    
}
