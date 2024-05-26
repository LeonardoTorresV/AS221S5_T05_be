package pe.edu.vallegrande.cassiatec.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;
import pe.edu.vallegrande.cassiatec.repository.TraduccionesGuardadasRepository;

@Service
public class TraduccionesGuardadasService {

    @Autowired
    private TraduccionesGuardadasRepository repository;

    public Optional<TraduccionesGuardadas> findById(Long id) {
        return repository.findById(id);
    }

    public List<TraduccionesGuardadas> obtenerTodasTraducciones() {
        return repository.findAll();
    }

    public List<TraduccionesGuardadas> findAllActive() {
        return repository.findByEstado("A");
    }

    public List<TraduccionesGuardadas> findAllInactive() {
        return repository.findByEstado("I");
    }

    public TraduccionesGuardadas guardarTraduccion(TraduccionesGuardadas traduccion) {
        return repository.save(traduccion);
    }

    @Transactional
    public void deletedLogical(Long id) {
        repository.deleteLogicoById(id);
    }

    @Transactional
    public void activate(Long id) {
        repository.activateById(id);
    }

    public void eliminarTraduccionPorId(Long id) {
        repository.deleteById(id);
    }
}