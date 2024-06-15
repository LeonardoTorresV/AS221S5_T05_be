package pe.edu.vallegrande.cassiatec.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "traducciones_guardadas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraduccionesGuardadas {
    @Id
    private String id_traducciones_guardadas;
    private String palabra_ingresada;
    private String palabra_traducida;
    private String estado; // Active or Inactive
}