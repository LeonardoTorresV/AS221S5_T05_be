-- Poner en uso la base de datos master.
	USE master;
	GO

-- Eliminar y crear la base de datos CASSIATEC
	DROP DATABASE IF EXISTS TRADUCTOR;
	CREATE DATABASE TRADUCTOR;
	GO

-- Poner en uso la base de datos CASSIATEC.
	USE TRADUCTOR;
	GO

-- Configurar el idioma espa�ol el motor de base de datos.
	SET LANGUAGE Espa�ol
	GO
	SELECT @@language AS 'Idioma'
	GO

-- Configurar el formato de fecha en dmy (d�a, mes y a�o) en el motor de base de datos.
	SET DATEFORMAT dmy
	GO


-- TABLES
-- Table: traduccionesGuardadas
CREATE TABLE traduccionesGuardadas (
    code INT IDENTITY(1,1) PRIMARY KEY,
    palabraIngresada VARCHAR(70) NOT NULL,
    palabraTraducida VARCHAR(70) NOT NULL
);
GO

SELECT * FROM traduccionesGuardadas