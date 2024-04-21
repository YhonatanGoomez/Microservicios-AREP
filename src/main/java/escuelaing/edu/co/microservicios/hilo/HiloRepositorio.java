package escuelaing.edu.co.microservicios.hilo;

import java.util.List;

import escuelaing.edu.co.microservicios.post.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HiloRepositorio {

    private HiloDAO hiloDAO;

    @Inject
    public HiloRepositorio(HiloDAO hiloDAO) {
        this.hiloDAO = hiloDAO;
    }

    public Hilo agregarHilo(Hilo hilo) throws Exception {
        return hiloDAO.agregarHilo(hilo);
    }

    public List<Hilo> consultarHilos(){
        return hiloDAO.consultarHilos();
    }

    public Hilo consultarHiloPorId(String id) throws Exception {
        return hiloDAO.consultarHiloPorId(id);
    }

    public void agregarPostAlHilo(String id, Post post) throws Exception  {
        hiloDAO.agregarPostAlHilo(id, post);
    }
    
}
