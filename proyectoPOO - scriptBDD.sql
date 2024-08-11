create database PROYECTO_POO;
use PROYECTO_POO;

-- Creacion de una tabla para la validacion de credenciales del ADMINISTRADOR
create table logue_administrador(
id_administrador int auto_increment primary key not null,
usuario varchar(50) not null,
contrasenia varchar(50) not null
);

insert into logue_administrador(usuario,contrasenia) values 
("Ariel Catucuamba","AC_ADM*_*"),("Jimena DiaZ","jd_ADM*_*");
SELECT * FROM logue_administrador;

 


-- Creacion de una tabla para la validacion de credenciales del RECEPCIONISTA
create table logueo_recepcionista(
id_recepcionista int auto_increment primary key not null,
usuario varchar(50) not null,
contrasenia varchar(50) not null
);
insert into logueo_recepcionista(usuario,contrasenia) values 
("Mishell Melba","MM_REC*_*"),("Fausto Pulig","FP_REC*_*");
SELECT * FROM logueo_recepcionista;





-- Creacion de una tabla para la validacion de credenciales de los clientes
create table logueo_clientes(
id_cliente int auto_increment primary key not null,
usuario varchar(50) not null,
contrasenia varchar(50) not null
);

insert into logueo_clientes(usuario,contrasenia) values 
("Rosa Zoria","RZ_CLI*_*"),("Lula Simbaña","LS_CLI*_*");
SELECT * FROM logueo_clientes;



-- Creacion de una tabla para la infromacion de los administradores
create table informacion_administrador(
id_administrador2 int auto_increment primary key not null,
nombre varchar(50) not null, 
apellido varchar(50) not null,
fecha_nacimiento date not null,
telefono varchar(15) not null,
email varchar(50) not null,
direccion varchar(100) not null,
fecha_contratacion date,
salario decimal(8,2)
-- podrian ir referenciado a otra tabla OJO
);

insert into informacion_administrador(nombre,apellido,fecha_nacimiento,telefono,email,direccion,fecha_contratacion,salario)
values ("Ariel","Catucuamba","2004-12-28","0988776521","ariel.hostalC@gmail.com","Colibri AV.Calandiras","2022-11-10",500.54),
("Jimena","Diaz","1995-07-20","09688001621","jimena.hostalC@gmail.com","Selva Alegre AV.Conquista","2000-04-17",1000.74);

select * from informacion_administrador;
update informacion_recepcionista set nombre="David" where id_recepcionista2=7;

-- Creacion de una tabla para la infromacion de los Recepcionistas
create table informacion_recepcionista(
id_recepcionista2 int auto_increment primary key not null,
nombre varchar(50) not null, 
apellido varchar(50) not null,
fecha_nacimiento date not null,
telefono varchar(15) not null,
email varchar(50) not null,
direccion varchar(100) not null,
fecha_contratacion date,
salario decimal(8,2),
turno varchar(50),
-- FK
id_admin int,
foreign key (id_admin) references informacion_administrador(id_administrador2)

);

insert into informacion_recepcionista(nombre,apellido,fecha_nacimiento,telefono,email,direccion,fecha_contratacion,salario,turno,id_admin) 
values ("Mishell","Melba","2000-08-15","0977692343","mishell.hostalC@gmail.com","Capelo AV.España","2021-05-23",600.54,"Vespertino",1),
("Fausto","Pulig","1999-06-15","0987994532","fausto.hostalC@gmail.com","Sangolqui AV.Barcelona","2019-08-20",578.54,"Matutino",2);
select * from informacion_recepcionista;

-- aaaaaaaaaaaaaaaaaaaaaaaaa
 alter table informacion_recepcionista add column imagen blob;















-- Creacion de una tabla para la infromacion de los clientes
create table informacion_clientes(
id_cliente2 int auto_increment primary key not null,
nombre varchar(50) not null,
apellido varchar(50) not null,
telefono varchar(15) not null, 
email varchar(50) not null,
direccion varchar(100) not null,
fecha_registro date,
-- FK
id_recepcionista int,
foreign key (id_recepcionista) references informacion_recepcionista(id_recepcionista2)
-- cedula varchar(30) y imagen blob
);
select * from informacion_clientes;

alter table informacion_clientes add column cedula varchar(30);
alter table informacion_clientes add column imagen blob;

update informacion_clientes set cedula="1727884767" where id_cliente2=5;
update informacion_clientes set cedula="1727997650" where id_cliente2=6;

-- ("Rosa Zoria","RZ_CLI*_*"),("Lula Simbaña","LS_CLI*_*")
insert into informacion_clientes(nombre,apellido,telefono,email,direccion,fecha_registro,id_recepcionista) values 
("Rosa","Zoria","0988776655","Rosa.zoria@gmail.com","Tubos AV.Bolviar","2010-12-23",3),
("Lula","Simbaña","09896501","Lula.Simbaña@gmail.com","Cashapamba AV.Madrid","2020-11-23",4);
select * from informacion_clientes;

-- Creacion de una tabla para la infromacion de las habitaciones
create table informacion_habitaciones (
    id_habitacion int primary key auto_increment,
    tipo varchar(50) not null,
    descripcion text,
    precio decimal(10, 2) not null, 
    estado varchar(20),
    numero_camas int not null,
    capacidad int not null, 
    tiene_wifi varchar(4) not null,
    tiene_tv varchar(4) not null,
    tiene_aire_acondicionado varchar(4) not null,
    tiene_minibar varchar(4) not null,
    vista varchar(50),
    -- FK
    id_administrador int,
    foreign key (id_administrador) references informacion_administrador(id_administrador2)
);
-- de aqui 
alter table informacion_habitaciones modify column tiene_wifi varchar(20);
alter table informacion_habitaciones modify column tiene_tv varchar(20);
alter table informacion_habitaciones modify column tiene_aire_acondicionado varchar(20);
alter table informacion_habitaciones modify column tiene_minibar varchar(20);

update informacion_habitaciones set tiene_wifi="Tiene" where id_habitacion=3 or id_habitacion=4;
update informacion_habitaciones set tiene_tv="Tiene" where id_habitacion=3 or id_habitacion=4;
update informacion_habitaciones set tiene_aire_acondicionado="Tiene" where id_habitacion=3 or id_habitacion=4;
update informacion_habitaciones set tiene_minibar="Tiene" where id_habitacion=3 or id_habitacion=4;



insert into informacion_habitaciones(tipo,descripcion,precio,estado,numero_camas,capacidad,tiene_wifi,tiene_tv,
tiene_aire_acondicionado,tiene_minibar,vista,id_administrador) 
values ("Familiar","Recamara perfecta para familias de 4 personas",200.80,"Disponible",4,4,"Si","Si","No","Si","Sin vista",1),
("Personal","Recamara perfecta para una persona",100.50,"Disponible",1,1,"Si","No","No","No","Vista al mar",1);
select * from informacion_habitaciones;

-- aaaaaaaaaaaaaaaaaaaaaaaaa
 alter table informacion_habitaciones add column imagen blob;
 
-- Creacion de una tabla para la infromacion de las reservas

create table informacion_reservas(
id_reserva int primary key auto_increment,
fecha_reserva date not null,
fecha_entrada date not null, 
fecha_salida date not null,
numero_huespedes int not null,
-- FK
id_habitacion int,
id_recepcionista int,
id_cliente int,
foreign key (id_habitacion) references informacion_habitaciones(id_habitacion),
foreign key (id_recepcionista) references informacion_recepcionista(id_recepcionista2),
foreign key (id_cliente) references informacion_clientes(id_cliente2)
);

insert into informacion_reservas(fecha_reserva,fecha_entrada,fecha_salida,numero_huespedes,id_habitacion,id_recepcionista,id_cliente)
 values ("2019-12-20","2019-12-23","2019-12-25",1,2,4,5),
 ("2021-12-20","2021-12-23","2021-12-25",1,2,4,5);
 select * from informacion_reservas;
 
 
 create table imagenes_recepcionista(
 id_imagen_Recepcionista int auto_increment primary key,
 cedula varchar(20),
 imagen longblob
 );
 
 -- aa



 select * from informacion_reservas;
 select * from informacion_habitaciones;
select * from informacion_clientes;
select * from informacion_recepcionista;
select * from informacion_administrador;

