package pe.edu.vallegrande.cassiatec.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraduccionesGuardadasDTO {

    private String id;
    private String palabra_ingresada;
    private String palabra_traducida;
    private String estado; // Active or Inactive
}
