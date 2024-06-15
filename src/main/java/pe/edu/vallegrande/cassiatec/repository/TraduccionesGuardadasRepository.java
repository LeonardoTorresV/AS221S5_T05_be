package pe.edu.vallegrande.cassiatec.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.*;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;
import reactor.core.publisher.Flux;

@EnableReactiveMongoRepositories
public interface TraduccionesGuardadasRepository extends ReactiveMongoRepository<TraduccionesGuardadas, String> {
    Flux<TraduccionesGuardadas> findByEstado(String estado);
}
