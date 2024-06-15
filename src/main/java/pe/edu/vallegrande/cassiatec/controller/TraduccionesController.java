package pe.edu.vallegrande.cassiatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;
import pe.edu.vallegrande.cassiatec.Service.TraduccionesGuardadasService;
import pe.edu.vallegrande.cassiatec.dto.TraduccionesGuardadasDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/traducciones")
public class TraduccionesController {

    @Autowired
    private TraduccionesGuardadasService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TraduccionesGuardadas> createTraduction(@RequestBody TraduccionesGuardadasDTO traduccionesGuardadas){
        return service.addTraduction(traduccionesGuardadas);
    }

    @GetMapping
    public Flux<TraduccionesGuardadas> getTraductions(){
        return service.findAllTratuctions();
    }

    @GetMapping("/id/{id_traducciones_guardadas}")
    public Mono<TraduccionesGuardadas> getTraduccionesGuardadas(@PathVariable String id_traducciones_guardadas){
        return service.getTraductionById(id_traducciones_guardadas);
    }

    @PutMapping("/{id}")
    public Mono<TraduccionesGuardadas> updateTraduction(@PathVariable String id, @RequestBody TraduccionesGuardadasDTO traduccionesGuardadasDTO) {
        return service.updateTraduction(id, traduccionesGuardadasDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTraduction(@PathVariable String id) {
        return service.deleteTraduction(id);
    }

    @PutMapping("/reactivate/{id}")
    public Mono<TraduccionesGuardadas> reactivateTraduccionesGuardadas(@PathVariable String id) {
        return service.reactivateTraduccionesGuardadas(id);
    }

    @GetMapping("/active")
    public Flux<TraduccionesGuardadas> getActive() {
        return service.getActive();
    }

    @GetMapping("/inactive")
    public Flux<TraduccionesGuardadas> getInactive() {
        return service.getInactive();
    }


    @DeleteMapping("/physically/{id}")
    public Mono<String> deletePhysicallyTraduccionesGuardadas(@PathVariable String id){
        return service.deletePhysicallyTraduccionesGuardadas(id);
    }
}