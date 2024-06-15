package pe.edu.vallegrande.cassiatec.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.cassiatec.dto.TraduccionesGuardadasDTO;
import pe.edu.vallegrande.cassiatec.repository.TraduccionesGuardadasRepository;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TraduccionesGuardadasService {

    @Autowired
    private TraduccionesGuardadasRepository repository;

    // CREATE, READ, UPDATE AND DELETE

    public Mono<TraduccionesGuardadas> addTraduction(TraduccionesGuardadasDTO traduccionesGuardadasDTO) {
        TraduccionesGuardadas traduccionesGuardadas = new TraduccionesGuardadas();
        traduccionesGuardadas.setPalabra_ingresada(traduccionesGuardadasDTO.getPalabra_ingresada());
        traduccionesGuardadas.setPalabra_traducida(traduccionesGuardadasDTO.getPalabra_traducida());
        traduccionesGuardadas.setEstado("A");
        return repository.save(traduccionesGuardadas);
    }

    public Flux<TraduccionesGuardadas> findAllTratuctions() {
        return repository.findAll();
    }

    public Mono<TraduccionesGuardadas> getTraductionById(String id_traducciones_guardadas) {
        return repository.findById(id_traducciones_guardadas);
    }

    public Mono<TraduccionesGuardadas> updateTraduction(String id_traducciones_guardadas,
                                                        TraduccionesGuardadasDTO traduccionesGuardadasRequest) {
        return repository.findById(id_traducciones_guardadas).flatMap(TraduccionesGuardadas -> {
            TraduccionesGuardadas.setPalabra_ingresada(traduccionesGuardadasRequest.getPalabra_ingresada());
            TraduccionesGuardadas.setPalabra_traducida(traduccionesGuardadasRequest.getPalabra_traducida());
            return repository.save(TraduccionesGuardadas);
        });

    }

    public Mono<Void> deleteTraduction(String id_traducciones_guardadas) {
        return repository.findById(id_traducciones_guardadas).flatMap(traduction -> {
            traduction.setEstado("I");
            return repository.save(traduction).then(Mono.empty());
        });
    }

    public Mono<TraduccionesGuardadas> reactivateTraduccionesGuardadas(String id) {
        return repository.findById(id).flatMap(traduction -> {
            if (traduction != null && traduction.getEstado().equals("I")) {
                traduction.setEstado("A");
                return repository.save(traduction);
            } else {
                return Mono
                        .error(new IllegalArgumentException("El formulario de contacto no está inactivo o no existe"));
            }
        });
    }

    public Flux<TraduccionesGuardadas> getActive() {
        return repository.findByEstado("A");
    }

    public Flux<TraduccionesGuardadas> getInactive() {
        return repository.findByEstado("I");
    }

    public Mono<String> deletePhysicallyTraduccionesGuardadas(String id) {
        return repository.deleteById(id)
                .then(Mono.just(id + " Eliminado"));
    }

}