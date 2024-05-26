package pe.edu.vallegrande.cassiatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;
@Repository
public interface TraduccionesGuardadasRepository extends JpaRepository<TraduccionesGuardadas, Long> {
    // Puedes definir métodos personalizados aquí si necesitas realizar consultas específicas

    List<TraduccionesGuardadas> findByEstado(String estado);

    @Modifying
    @Transactional
    @Query("UPDATE TraduccionesGuardadas t SET t.estado = 'I' WHERE t.id_traducciones_guardadas = :id")
    void deleteLogicoById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE TraduccionesGuardadas t SET t.estado = 'A' WHERE t.id_traducciones_guardadas = :id")
    void activateById(Long id);

}