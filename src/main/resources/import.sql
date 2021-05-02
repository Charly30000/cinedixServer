/* Creamos algunos usuarios */
INSERT INTO `usuarios` (id, username, password, enabled, email) VALUES (1, 'andres', '$2a$10$scI9Ux72eyjCwV8nEKo8euCno9WHK0hrq9WNzjCe4mBlssbX3Gki6', 1, 'andres@correo.com');
INSERT INTO `usuarios` (id, username, password, enabled, email) VALUES (2, 'admin', '$2a$10$dPlyFSQjGIx1CJzwh5cIt.GTt6XbdYQ6Zlo.u5QGTjNhzMqgYcPRm', 1, 'admin@correo.com');
INSERT INTO `usuarios` (id, username, password, enabled, email) VALUES (3, 'angel', '$2a$10$scI9Ux72eyjCwV8nEKo8euCno9WHK0hrq9WNzjCe4mBlssbX3Gki6', 1, 'angel@correo.com');

/* Creamos algunos roles de usuario */
INSERT INTO `authorities` (usuario_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO `authorities` (usuario_id, authority) VALUES (2, 'ROLE_ADMIN');
INSERT INTO `authorities` (usuario_id, authority) VALUES (2, 'ROLE_USER');
INSERT INTO `authorities` (usuario_id, authority) VALUES (3, 'ROLE_USER');

/* Creamos algunos cines */
INSERT INTO `cines` (id, nombre, localizacion) VALUES (1, 'cinedix fuenlabrada', 'fuenlabrada');
INSERT INTO `cines` (id, nombre, localizacion) VALUES (2, 'cinedix getafe', 'getafe');
INSERT INTO `cines` (id, nombre, localizacion) VALUES (3, 'cinedix mostoles', 'mostoles');
INSERT INTO `cines` (id, nombre, localizacion) VALUES (4, 'cinedix alcorcon', 'alcorcon');

/* Creamos algunas peliculas */
INSERT INTO `peliculas` (id, nombre, descripcion, ruta_imagen, estreno) VALUES (1, 'Los vengadores', 'Los vengadores se forman', null, 1);
INSERT INTO `peliculas` (id, nombre, descripcion, ruta_imagen, estreno) VALUES (2, 'Jimmy neutron', 'Jimmy y sus amigos tienen que ir a buscar a sus padres secuestrados por... Alienigenas!', null, 1);
INSERT INTO `peliculas` (id, nombre, descripcion, ruta_imagen, estreno) VALUES (3, 'Bob esponja', 'La gran pelicula sobre Bob esponja y sus amigos! Dedicada principalmente para los niños!', null, 1);
INSERT INTO `peliculas` (id, nombre, descripcion, ruta_imagen, estreno) VALUES (4, 'Kimetsu no yaiba', 'Tanjiro y sus amigos deberan de ir a buscar respuestas sobre su padre, pero... algo extraño sucederá en el tren que les llevará a las respuestas!', null, 1);
INSERT INTO `peliculas` (id, nombre, descripcion, ruta_imagen, estreno) VALUES (5, '7 apellidos vascos', 'Una gran comedia española donde varias familias se encontraran y ocurriran cosas muy divertidas', null, 0);

/* Decimos que peliculas tiene cada cine */
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (1, 1);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (1, 2);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (1, 3);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (1, 4);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (1, 5);

INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (2, 1);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (2, 2);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (2, 3);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (2, 4);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (2, 5);

INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (3, 1);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (3, 2);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (3, 3);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (3, 4);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (3, 5);

INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (4, 1);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (4, 2);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (4, 3);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (4, 4);
INSERT INTO `cines_peliculas` (cine_id, peliculas_id) VALUES (4, 5);

/* Insertamos sesiones_peliculas */
INSERT INTO `sesiones_peliculas` (id, cantidad_sitios_disponibles, hora_pelicula, cine_id, pelicula_id) VALUES (1, 26, NOW(), 1, 1);
INSERT INTO `sesiones_peliculas` (id, cantidad_sitios_disponibles, hora_pelicula, cine_id, pelicula_id) VALUES (2, 29, NOW(), 1, 2);
INSERT INTO `sesiones_peliculas` (id, cantidad_sitios_disponibles, hora_pelicula, cine_id, pelicula_id) VALUES (3, 30, NOW(), 1, 3);

INSERT INTO `sesiones_peliculas` (id, cantidad_sitios_disponibles, hora_pelicula, cine_id, pelicula_id) VALUES (4, 30, NOW(), 2, 1);
INSERT INTO `sesiones_peliculas` (id, cantidad_sitios_disponibles, hora_pelicula, cine_id, pelicula_id) VALUES (5, 30, NOW(), 2, 2);
INSERT INTO `sesiones_peliculas` (id, cantidad_sitios_disponibles, hora_pelicula, cine_id, pelicula_id) VALUES (6, 30, NOW(), 2, 3);

/* Insertamos entradas */
INSERT INTO `entradas` (id, codigo, fecha, sesion_pelicula_id, usuario_id) VALUES (1, 'abdefgh', NOW(), 1, 1);
INSERT INTO `entradas` (id, codigo, fecha, sesion_pelicula_id, usuario_id) VALUES (2, 'abdefyg', NOW(), 1, 1);
INSERT INTO `entradas` (id, codigo, fecha, sesion_pelicula_id, usuario_id) VALUES (3, 'abdeegh', NOW(), 1, 1);
INSERT INTO `entradas` (id, codigo, fecha, sesion_pelicula_id, usuario_id) VALUES (4, 'abdfngh', NOW(), 1, 2);

INSERT INTO `entradas` (id, codigo, fecha, sesion_pelicula_id, usuario_id) VALUES (5, 'aidefgh', NOW(), 2, 3);


/* Insertamos sitios_ocupados */
INSERT INTO `sitios_ocupados` (id, sitio_ocupado, entrada_id, sesion_pelicula_id) VALUES (1, 25, 1, 1);
INSERT INTO `sitios_ocupados` (id, sitio_ocupado, entrada_id, sesion_pelicula_id) VALUES (2, 26, 2, 1);
INSERT INTO `sitios_ocupados` (id, sitio_ocupado, entrada_id, sesion_pelicula_id) VALUES (3, 27, 3, 1);
INSERT INTO `sitios_ocupados` (id, sitio_ocupado, entrada_id, sesion_pelicula_id) VALUES (4, 15, 4, 1);

INSERT INTO `sitios_ocupados` (id, sitio_ocupado, entrada_id, sesion_pelicula_id) VALUES (5, 18, 5, 2);








