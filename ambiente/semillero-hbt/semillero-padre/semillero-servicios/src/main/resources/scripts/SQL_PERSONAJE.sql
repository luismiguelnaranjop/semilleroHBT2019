CREATE TABLE PERSONAJE (
  SPID number(10) not null,
  SPNOMBRE varchar2(50) not null,
  SPID_COMIC number(3) not null,
  SPESTADO varchar2(50 char) not null,
  SPSUPERPODER varchar2(200 char) not null,
  CONSTRAINT "PK_PERSONAJE" PRIMARY KEY (SPID),
  CONSTRAINT "CHK_PERSONAJE_ESTADO" CHECK (SPESTADO IN('ACTIVO','INACTIVO')),
  UNIQUE(SPNOMBRE),  
);

comment on column PERSONAJE.SPID is 'Identificador unico del personaje';
comment on column PERSONAJE.SPNOMBRE is 'Nombre del personaje';
comment on column PERSONAJE.SPID_COMIC is 'Identificador unico del comic al que pertenece el personaje';
comment on column PERSONAJE.SPESTADO is 'Define el estado del personaje, si está activo o nó';
comment on column PERSONAJE.SPSUPERPODER is 'Super poder el personaje';

--Notas a tener en cuenta
--SP indica el prefijo de la tabla S=semillero, P=personaje
--Borrar la estructura de la tabla: drop table "DB_SEMILLERO"."PERSONAJE";
--Ver estructura de la tabla: desc DB_SEMILLERO"."PERSONAJE;
--Consultar los datos de una tabla completa: select * from PERSONAJE
--Consultar solo unos campos en especifico (seleccionar el registro con el id = 1): select * from PERSONAJE where SPID = 1
--borrar un elemento: delete PERSONAJE where SPID = 3
--confirmar los cambios: commit
--devolver los cambios: rollback

--Crear una secuencia para utilizarla como incremento del identificador
CREATE SEQUENCE "SEQ_PERSONAJE" MINVALUE 1 START WITH 1 INCREMENT BY 1;

--Asi obtenemos el siguiente valor: SELECT "DB_SEMILLERO"."SEQ_PERSONAJE".nextval FROM DUAL;
--Tambien podemos obtener el valor actual: SELECT "DB_SEMILLERO"."SEQ_PERSONAJE".CURRVAL FROM DUAL;

--Así añadimos las llaves foraneas
ALTER TABLE PERSONAJE 
ADD CONSTRAINT "FK_ID_COMIC"
   FOREIGN KEY (SPID_COMIC)
   REFERENCES comic (SPID);

--Como insertar datos:
INSERT INTO PERSONAJE (SPID, SPNOMBRE, SPID_COMIC, SPID_ROL, SPESTADO, SPSUPERPODER)
    VALUES (SEC_PERSONAJE.nextval, 'BATMAN', 1, 1, 'ACTIVO', 'MILLONARIO');
COMMIT;