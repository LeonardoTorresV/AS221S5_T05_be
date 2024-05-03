package pe.edu.vallegrande.cassiatec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        return service.obtenerTodasTraducciones();
    }

    @PostMapping
    public TraduccionesGuardadas guardarTraduccion(@RequestBody TraduccionesGuardadas traduccion) {
        System.out.println("=================================");
        System.out.println("Se inserto correctamente: " + traduccion);
        System.out.println("=================================");
        return service.guardarTraduccion(traduccion);
    }

    @DeleteMapping("/{id}")
    public void eliminarTraduccion(@PathVariable Long id) {
        service.eliminarTraduccionPorId(id);
        System.out.println("=================================");
        System.out.println("Se elimino correctamente: " + id);
        System.out.println("=================================");
    }
}