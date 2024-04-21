package escuelaing.edu.co.microservicios.hilo;

import java.util.List;


import escuelaing.edu.co.microservicios.post.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HiloServicioImpl implements HiloServicio {

    private HiloRepositorio hiloRepositorio;

    @Inject
    public HiloServicioImpl(HiloRepositorio hiloRepositorio) {
        this.hiloRepositorio = hiloRepositorio;
    }

    @Override
    public Hilo agregarHilo(Hilo hilo) throws Exception {
        return hiloRepositorio.agregarHilo(hilo);
    }

    @Override
    public List<Hilo> consultarHilos(){
        return hiloRepositorio.consultarHilos();
    }

    @Override
    public Hilo consultarHiloPorId(String id) throws Exception {
        return hiloRepositorio.consultarHiloPorId(id);
    }

    @Override
    public void agregarPostAlHilo(String id, Post post) throws Exception {
        if(post.getComentario().length() > 140){
            throw new Exception("El comentario supera los 140 caracteres");
        }
        hiloRepositorio.agregarPostAlHilo(id, post);
    }
    
}
