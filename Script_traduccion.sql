CREATE TABLE traducciones_guardadas (
    id_traducciones_guardadas NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    palabra_ingresada VARCHAR2(70) NOT NULL,
    palabra_traducida VARCHAR2(70) NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'I'))
);

INSERT INTO traducciones_guardadas (palabra_ingresada, palabra_traducida)
VALUES ('horse', 'caballo');

select * from traducciones_guardadas;