package pe.edu.vallegrande.cassiatec.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "traducciones_guardadas")
public class TraduccionesGuardadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_traducciones_guardadas")
    private Long id_traducciones_guardadas;
    
    @Column(name = "palabra_ingresada")
    private String palabra_ingresada;

    @Column(name = "palabra_traducida")
    private String palabra_traducida;

    @Column(name = "estado")
    @Builder.Default // Utilizamos @Builder con valor predeterminado
    private String estado = "A"; // Valor predeterminado para el estado
}