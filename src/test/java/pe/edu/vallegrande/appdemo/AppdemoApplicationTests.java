package pe.edu.vallegrande.appdemo;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import pe.edu.vallegrande.cassiatec.model.entity.TraduccionesGuardadas;
import pe.edu.vallegrande.cassiatec.TraductorIA_Application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = TraductorIA_Application.class)
class AppdemoApplicationTests {

	@Test
	void contextLoads() {
	    TraduccionesGuardadas traduccionesGuardadas = new TraduccionesGuardadas();
	    traduccionesGuardadas.setId_traducciones_guardadas(1L);
	    assertNotNull(traduccionesGuardadas.getId_traducciones_guardadas());
	}
}
