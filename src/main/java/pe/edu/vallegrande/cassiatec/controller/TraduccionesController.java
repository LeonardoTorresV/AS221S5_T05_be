package pe.edu.vallegrande.cassiatec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import pe.edu.vallegrande.cassiatec.Service.TraduccionesGuardadasService;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;


@RestController
@RequestMapping("/traducciones")
public class TraduccionesController {

    @Autowired
    private TraduccionesGuardadasService service;

    @GetMapping
    public List<TraduccionesGuardadas> obtenerTodasTraducciones() {
        System.out.println("=================================");
        System.out.println("Se obtuvo todas las traducciones");
        System.out.println("=================================");
        return service.obtenerTodasTraducciones();
    }

    @GetMapping("/activos")
    public ResponseEntity<List<TraduccionesGuardadas>> getAllActive() {
        List<TraduccionesGuardadas> activos = service.findAllActive();
        System.out.println("=================================");
        System.out.println("Se obtuvo todas las traducciones activas");
        System.out.println("=================================");
        return ResponseEntity.ok(activos);
    }

    @GetMapping("/inactivos")
    public ResponseEntity<List<TraduccionesGuardadas>> getAllInactive() {
        List<TraduccionesGuardadas> inactivos = service.findAllInactive();
        System.out.println("=================================");
        System.out.println("Se obtuvo todas las traducciones inactivas");
        System.out.println("=================================");
        return ResponseEntity.ok(inactivos);
    }

    @PostMapping
    public TraduccionesGuardadas guardarTraduccion(@RequestBody TraduccionesGuardadas traduccion) {
        System.out.println("=================================");
        System.out.println("Se creo correctamente: " + traduccion);
        System.out.println("=================================");
        return service.guardarTraduccion(traduccion);
    }

    @PutMapping
    public ResponseEntity<TraduccionesGuardadas> update(@RequestBody TraduccionesGuardadas traduccion) {
        // Verifica si se proporcionó un ID
        if (traduccion.getId_traducciones_guardadas() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Verifica si el recurso existe
        Optional<TraduccionesGuardadas> existingTraduccion = service.findById(traduccion.getId_traducciones_guardadas());
        if (!existingTraduccion.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Guarda la actualización
        TraduccionesGuardadas updatedTraduccion = service.guardarTraduccion(traduccion);
        System.out.println("=================================");
        System.out.println("Se actualizo correctamente");
        System.out.println("=================================");
        return ResponseEntity.ok(updatedTraduccion);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deletedLogical(@PathVariable Long id) {
        service.deletedLogical(id);
        System.out.println("=================================");
        System.out.println("Se elimino logicamente: " + id);
        System.out.println("=================================");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        service.activate(id);
        System.out.println("=================================");
        System.out.println("Se activo logicamente: " + id);
        System.out.println("=================================");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void eliminarTraduccion(@PathVariable Long id) {
        service.eliminarTraduccionPorId(id);
        System.out.println("=================================");
        System.out.println("Se elimino correctamente: " + id);
        System.out.println("=================================");
    }
}