package escuelaing.edu.co.microservicios.hilo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import escuelaing.edu.co.microservicios.post.Post;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class HiloDAO {
    
    private HashMap<String, Hilo> hilos;

    public HiloDAO() {
        this.hilos = new HashMap<>();
        hilos.put("1", new Hilo("1", "Yhonatan Gomez", new ArrayList<>()));
    }

    public Hilo agregarHilo(Hilo hilo) throws Exception {
        if(hilos.containsKey(hilo.getId()) || hilo.getId() == null){
            throw new Exception("El hilo ya existe o el id es nulo");
        }
        return hilos.put(hilo.getId(), hilo);
    }

    public List<Hilo> consultarHilos(){
        return  hilos.values().stream().toList();
    }

    public Hilo consultarHiloPorId(String id) throws Exception {
        if (!hilos.containsKey(id)) {
            throw new Exception("El hilo no existe");
            
        }
        return hilos.get(id);
    }

    public void agregarPostAlHilo(String id, Post post) throws Exception{
        Hilo hilo = consultarHiloPorId(id);
        hilo.agregarPost(post);
    }
}
