INSERT INTO CATEGORIA (id, nombre, descripcion)  VALUES (NEXTVAL('hibernate_sequence'), 'Sala del trono', 'Un lugar para que la alta aristocracia se relaje');
INSERT INTO CATEGORIA (id, nombre, descripcion)  VALUES (NEXTVAL('hibernate_sequence'), 'Presentaciones', 'Preséntate');
INSERT INTO CATEGORIA (id, nombre, descripcion)  VALUES (NEXTVAL('hibernate_sequence'), 'Problemas técnicos', 'Sala para solventar problemas con el foro o el juego');
INSERT INTO CATEGORIA (id, nombre, descripcion)  VALUES (NEXTVAL('hibernate_sequence'), 'DragonFlight', 'Habla sobre la nueva expansión de World of Warcraft');

INSERT INTO HILO (id, nombre, likes, tipo_hilo, contenido, fecha_creacion, creador, categoria_id) VALUES (NEXTVAL('hibernate_sequence'), 'Empieza a hacer mucho calor en el reino', 0, 'AVISO', 'Vaya calufa', {ts '2022-09-17 18:47:52.69'}, 'rey', 1);
INSERT INTO HILO (id, nombre, likes, tipo_hilo, contenido, fecha_creacion, creador, categoria_id) VALUES (NEXTVAL('hibernate_sequence'), 'Hola me llamo Ramiro', 0, 'NORMAL', 'Encantado de unirme al foro', {ts '2022-08-17 17:47:52.69'}, 'user', 2);
INSERT INTO HILO (id, nombre, likes, tipo_hilo, contenido, fecha_creacion, creador, categoria_id) VALUES (NEXTVAL('hibernate_sequence'), 'No veo los mensajes', 0, 'URGENTE', 'No veo ningun mensaje del foro', {ts '2022-02-17 15:47:52.69'}, 'user', 3);
INSERT INTO HILO (id, nombre, likes, tipo_hilo, contenido, fecha_creacion, creador, categoria_id) VALUES (NEXTVAL('hibernate_sequence'), 'No me gusta la nueva expansion', 0, 'NORMAL', 'No esta Arthas', {ts '2022-09-20 18:00:52.69'}, 'principe', 4);

INSERT INTO HILO (id, nombre, likes, tipo_hilo, contenido, fecha_creacion, creador, categoria_id) VALUES (NEXTVAL('hibernate_sequence'), '¿Habeis escuchado lo nuevo de Bad Bunny?', 0, 'NORMAL', 'La verdad es que son temones', {ts '2022-10-17 09:00:00.69'}, 'principe', 1);
INSERT INTO HILO (id, nombre, likes, tipo_hilo, contenido, fecha_creacion, creador, categoria_id) VALUES (NEXTVAL('hibernate_sequence'), 'Aviso a nuevos foreros', 0, 'AVISO', 'Seria conveniente que todos dediqueis un par de minutos a presentaros', {ts '2022-07-15 10:00:52.69'}, 'admin', 2);
INSERT INTO HILO (id, nombre, likes, tipo_hilo, contenido, fecha_creacion, creador, categoria_id) VALUES (NEXTVAL('hibernate_sequence'), 'No puedo entrar al juego', 0, 'URGENTE', 'Intento loguear y no me deja', {ts '2022-01-10 09:47:52.69'}, 'user', 3);
INSERT INTO HILO (id, nombre, likes, tipo_hilo, contenido, fecha_creacion, creador, categoria_id) VALUES (NEXTVAL('hibernate_sequence'), '¿Que dragon os vais a hacer?', 0, 'NORMAL', 'Yo me hare el negro', {ts '2022-03-19 10:00:10.69'}, 'user', 4);

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Vaya calufa', 'rey', {ts '2022-09-17 18:47:52.69'}, 'AVISO',  5);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Encantado de unirme al foro', 'user', {ts '2022-08-17 17:47:52.69'}, 'NORMAL',  6);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'No veo ningun mensaje del foro', 'user', {ts '2022-02-17 15:47:52.69'}, 'URGENTE',  7);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'No esta Arthas', 'principe', {ts '2022-09-20 18:00:52.69'}, 'NORMAL',  8);

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'La verdad es que son temones', 'principe', {ts '2022-10-17 09:00:00.69'}, 'NORMAL',  9);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Seria conveniente que todos dediqueis un par de minutos a presentaros', 'admin', {ts '2022-07-15 10:00:52.69'}, 'AVISO',  10);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Intento loguear y no me deja', 'user', {ts '2022-01-10 09:47:52.69'}, 'URGENTE',  11);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Yo me hare el negro', 'user', {ts '2022-03-19 10:00:10.69'}, 'NORMAL',  12);

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Eso es porque no has pagado lo suficiente', 'principe', {ts '2022-09-20 22:00:52.69'}, 'NORMAL',  7);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Es que es muy caro', 'user', {ts '2022-10-20 12:00:52.69'}, 'NORMAL',  7);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Ahora con la compra de microsoft seguro que baja de precio', 'rey', {ts '2022-10-20 14:00:52.69'}, 'NORMAL',  7);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Eso espero', 'user', {ts '2022-10-20 17:10:52.69'}, 'NORMAL',  7);

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Normal con la fecha en la que estamos', 'principe', {ts '2022-09-17 19:00:52.69'}, 'NORMAL',  5);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Yo creo que es el cambio climatico, deberian inventarse mas impuestos para los pobres', 'rey', {ts '2022-09-17 20:35:52.69'}, 'NORMAL',  5);

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Un saludo Ramiro, disfruta del foro', 'rey', {ts '2022-08-18 14:00:52.69'}, 'NORMAL',  6);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Grande Ramiro', 'principe', {ts '2022-08-20 17:10:52.69'}, 'NORMAL',  6);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Bienvenido y buen foro', 'admin', {ts '2022-08-20 18:15:52.69'}, 'NORMAL',  6);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Gracias a todos', 'user', {ts '2022-08-20 20:20:00.69'}, 'NORMAL',  6);

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'No ha salido aun y ya estamos quejandonos...', 'rey', {ts '2022-09-20 19:00:52.69'}, 'NORMAL',  8);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Pero mira el trailer no tiene nada de especial', 'principe', {ts '2022-10-21 12:00:52.69'}, 'NORMAL',  8);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Dales tiempo tio, si aun no hay ni fecha de salida', 'rey', {ts '2022-10-21 14:02:52.69'}, 'NORMAL',  8);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'A mi me gusta a pesar de la ausencia de fluber', 'user', {ts '2022-10-21 17:25:52.69'}, 'NORMAL',  8);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'xD', 'principe', {ts '2022-10-21 19:30:52.69'}, 'NORMAL',  8); 

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'No y dudo que lo haga', 'rey', {ts '2022-10-17 9:30:52.69'}, 'NORMAL',  9);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Del tiron, ademas me encanta despues de la playa', 'admin', {ts '2022-10-17 10:00:50.69'}, 'NORMAL',  9);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Venga rey dale un oportunidad', 'principe', {ts '2022-10-17 14:00:52.69'}, 'NORMAL',  9);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Paso xD', 'rey', {ts '2022-10-20 17:10:52.69'}, 'NORMAL',  9);

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Pulsa control alt f4', 'rey', {ts '2022-01-10 10:35:52.69'}, 'NORMAL',  11);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'jajajaja xD', 'principe', {ts '2022-01-10 11:30:50.69'}, 'NORMAL',  11);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), '¿Aqui no hay admin o que?', 'user', {ts '2022-01-10 14:00:52.69'}, 'NORMAL',  11);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Se recuerda a los nuevos usuarios que para poder jugar a world of warcraft tienen que tener una suscripcion activa del juego', 'admin', {ts '2022-01-13 19:10:52.69'}, 'AVISO',  11);

INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Otro negro por aqui', 'rey', {ts '2022-03-19 10:40:52.69'}, 'NORMAL',  12);
INSERT INTO MENSAJE (id, contenido, creador, fecha_creacion, tipo_mensaje, hilo_id) VALUES (NEXTVAL('hibernate_sequence'), 'Se viene rojo', 'principe', {ts '2022-03-20 12:30:50.69'}, 'NORMAL',  12);
