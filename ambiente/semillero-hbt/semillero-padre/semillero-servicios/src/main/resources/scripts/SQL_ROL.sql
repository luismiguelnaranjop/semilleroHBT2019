--Creación de una tabla rol para el personaje.

--El nombre del rol es unico
--Los estados solo pueden ser "ACTIVO e INACTIVO"

CREATE TABLE ROL(
  SRID number(10) not null,
  SRNOMBRE  varchar2(50) not null,
  SRESTADO varchar2(50 char) not null,
  CONSTRAINT PK_ROL PRIMARY KEY (SRID),
  CONSTRAINT UNIQUE_ROL_NAME UNIQUE(SRNOMBRE),
  CONSTRAINT "ROL_ESTADO_CHK" CHECK (SRESTADO IN('ACTIVO','INACTIVO'))
);

comment on column ROL.SRID is 'Identificador unico del rol del personaje';
comment on column ROL.SRNOMBRE is 'Nombre del rol';
comment on column ROL.SRESTADO is 'Define el estado del rol, si está activo o no';

--Notas a tener en cuenta
--SR indica el prefijo de la tabla S=semillero, R=rol
--Borrar la estructura de la tabla: drop table "DB_SEMILLERO"."ROL";
--Ver estructura de la tabla: desc DB_SEMILLERO"."ROL;
--Consultar los datos de una tabla completa: select * from ROL
--Consultar solo unos campos en especifico (seleccionar el registro con el id = 1): select * from ROL where SRID = 1
--borrar un elemento: delete PERSONAJE where SRID = 3
--confirmar los cambios: commit
--devolver los cambios: rollback


--Crear una secuencia para utilizarla como incremento del identificador
CREATE SEQUENCE "SEQ_ROL" MINVALUE 1 START WITH 1 INCREMENT BY 1;

--Asi obtenemos el siguiente valor: SELECT "DB_SEMILLERO"."SEQ_ROL".nextval FROM DUAL;
--Tambien podemos obtener el valor actual: SELECT "DB_SEMILLERO"."SEQ_ROL".CURRVAL FROM DUAL;


-- La relacion entre la tabla PERSONAJE y la tabla ROL es de muchos a uno 
-- puesto que un ROL puede estar asociado con varios personajes pero un 
-- PERSONAJE solo puede tener un Rol en el Comic, por ende el SRID debe ser 
-- llave foranea en la tabla PERSONAJE, así:

ALTER TABLE PERSONAJE
  ADD(
    SPID_ROL number(3) not null
  );
comment on column PERSONAJE.SPID_ROL is 'Identificador unico del rol del personaje';

-- La columna SPID_ROL agregada a la tabla Personaje es llave foranea y apunta
-- al SRID de la tabla ROL
ALTER TABLE PERSONAJE 
ADD CONSTRAINT "FK_ROL"
   FOREIGN KEY (SPID_ROL)
   REFERENCES ROL (SRID);

-- Los nombres de los personajes deben ser unicos
ALTER TABLE PERSONAJE
  ADD CONSTRAINT UNIQUE_SPNOMBRE UNIQUE(SPNOMBRE);
  
-- Así se insertan datos
INSERT INTO ROL (SRID, SRNOMBRE, SRESTADO)
    VALUES (SEQ_ROL.nextval, 'VILLANO', 'ACTIVO');
COMMIT;