package pe.edu.vallegrande.cassiatec.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;
import pe.edu.vallegrande.cassiatec.repository.TraduccionesGuardadasRepository;

@Service
public class TraduccionesGuardadasService {

    @Autowired
    private TraduccionesGuardadasRepository repository;

    public List<TraduccionesGuardadas> obtenerTodasTraducciones() {
        return repository.findAll();
    }

    public TraduccionesGuardadas guardarTraduccion(TraduccionesGuardadas traduccion) {
        return repository.save(traduccion);
    }

    public void eliminarTraduccionPorId(Long id) {
        repository.deleteById(id);
    }
}