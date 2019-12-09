CREATE TABLE "DB_SEMILLERO"."COMIC" (
"SCID" NUMBER(3,0) NOT NULL ENABLE,
"SCNOMBRE" VARCHAR2(50 BYTE) NOT NULL  ENABLE, 
"SCEDITORIAL" VARCHAR2(50 BYTE) NOT NULL  ENABLE, 
"SCTEMATICA" VARCHAR2(50 BYTE), 
"SCCOLECCION" VARCHAR2(50 BYTE) NOT NULL  ENABLE, 
"SCNUMEROPAGINAS" NUMBER(5,0) NOT NULL  ENABLE,
"SCPRECIO" NUMBER(4,0) NOT NULL  ENABLE,
"SCAUTORES" VARCHAR2(50 BYTE),
"SCCOLOR" CHAR , 
"SCFECHA_VENTA" date,
"SCESTADO" VARCHAR2(50 BYTE), 
"SCCANTIDAD" NUMBER(3,0) NOT NULL  ENABLE,
CONSTRAINT "COMIC_PK" PRIMARY KEY ("SCID"),
CONSTRAINT "COMIC_C_1" CHECK (SCTEMATICA IN ('AVENTURAS', 'BELICO', 'HUMORISTICO', 'DEPORTIVO', 'FANTASTICO', 'CIENCIA_FICCION', 'HISTORICO', 'HORROR')),
CONSTRAINT "COMIC_C_2" CHECK (SCCOLOR IN ('1', '0')),
CONSTRAINT "COMIC_C_3" CHECK (SCESTADO IN ('ACTIVO', 'INACTIVO'))
);

comment on column COMIC.SCNOMBRE is 'Nombre o titulo del comic o revista grafica';
comment on column COMIC.SCEDITORIAL is 'Empresa editorial quien publico el comic';
comment on column COMIC.SCTEMATICA is 'Permite identificar si el comic es de tipo AVENTURAS, BELICO, HUMORISTICO, DEPORTIVO, FANTASTICO, CIENCIA_FICCION, HISTORICO, HORROR';
comment on column COMIC.SCCOLECCION is 'Conjunto de publicaciones de la que el comic es parte';
comment on column COMIC.SCNUMEROPAGINAS is 'Cantidad de las paginas que contiene el comic';
comment on column COMIC.SCPRECIO is 'Valor en pesos del comic';
comment on column COMIC.SCAUTORES is 'Autor u autores del comic';
comment on column COMIC.SCCOLOR is 'Define si el comic es a color o no, campo de tipo booleano solo acepta 1 o 0';
comment on column COMIC.SCFECHA_VENTA is 'Fecha de inicio de la venta del comic';
comment on column COMIC.SCESTADO is 'Define si el comic tiene existencia para la venta o no' ;
comment on column COMIC.SCCANTIDAD is 'Cantidad de comics en inventario disponibles para la venta';
comment on column COMIC.SCID is 'Identificador unico del comic';

--Notas a tener en cuenta
--SC indica el prefijo de la tabla S=semillero, C=comic
--Borrar la estructura de la tabla: drop table "DB_SEMILLERO"."COMIC";
--Ver estructura de la tabla: desc DB_SEMILLERO"."COMIC;
--Consultar los datos de una tabla completa: select * from COMIC
--Consultar solo unos campos en especifico (seleccionar el registro con el id = 1): select * from COMIC where SCID = 1
--borrar un elemento: delete COMIC where SCID = 3
--confirmar los cambios: commit
--devolver los cambios: rollback

--Crear una secuencia para utilizarla como incremento del identificador
CREATE SEQUENCE "DB_SEMILLERO"."SEQ_COMIC" MINVALUE 1 START WITH 1  INCREMENT BY 1 NOCACHE;

--Asi obtenemos el siguiente valor: SELECT "DB_SEMILLERO"."SEQ_COMIC".nextval FROM DUAL;
--Tambien podemos obtener el valor actual: SELECT "DB_SEMILLERO"."SEQ_COMIC".CURRVAL FROM DUAL;

--Como insertar datos:
insert into COMIC (SCID,SCNOMBRE, SCEDITORIAL, SCTEMATICA, SCCOLECCION,SCNUMEROPAGINAS,SCPRECIO,SCAUTORES,SCCOLOR,SCFECHA_VENTA,SCESTADO,SCCANTIDAD)
values (DB_SEMILLERO.SEQ_COMIC.nextval,'Dragon Ball Yamcha','Planeta C�mic','AVENTURAS','Manga Shonen',144,2100,'Dragon Garow Lee',0,sysdate,'ACTIVO',20);
insert into COMIC (SCID,SCNOMBRE, SCEDITORIAL, SCTEMATICA, SCCOLECCION,SCNUMEROPAGINAS,SCPRECIO,SCAUTORES,SCCOLOR,SCFECHA_VENTA,SCESTADO,SCCANTIDAD)
values (SEQ_COMIC.nextval,'Captain America Corps 1-5 USA','Panini Comics ','FANTASTICO','BIBLIOTECA MARVEL ',128,5000,'Phillippe Briones, Roger Stern ',0,sysdate,'ACTIVO',5);
insert into COMIC (SCID,SCNOMBRE, SCEDITORIAL, SCTEMATICA, SCCOLECCION,SCNUMEROPAGINAS,SCPRECIO,SCAUTORES,SCCOLOR,SCFECHA_VENTA,SCESTADO,SCCANTIDAD)
values (SEQ_COMIC.nextval,'The Spectacular Spider-Man v2 USA','Panini Comics ','FANTASTICO','MARVEL COMICS',208,6225,'Straczynski,Deodato Jr.,Barnes,Eaton',1,sysdate,'INACTIVO',0);

select * from COMIC;