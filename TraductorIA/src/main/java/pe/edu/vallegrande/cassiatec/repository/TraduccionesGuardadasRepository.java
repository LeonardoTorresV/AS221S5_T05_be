package pe.edu.vallegrande.cassiatec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;
@Repository
public interface TraduccionesGuardadasRepository extends JpaRepository<TraduccionesGuardadas, Long> {
    // Puedes definir métodos personalizados aquí si necesitas realizar consultas específicas
}