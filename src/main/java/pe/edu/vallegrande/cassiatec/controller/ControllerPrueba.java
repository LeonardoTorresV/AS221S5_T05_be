package pe.edu.vallegrande.cassiatec.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ControllerPrueba {
	
	@GetMapping("/prueba")
	public String prueba() {
		return "Hola Castillo, esto es una prueba de los rest controller";
		
	}

}
