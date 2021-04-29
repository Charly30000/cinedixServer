/* Creamos algunos usuarios con sus roles */
INSERT INTO `users` (username, password, enabled, email) VALUES ('andres', '$2a$10$scI9Ux72eyjCwV8nEKo8euCno9WHK0hrq9WNzjCe4mBlssbX3Gki6', 1, 'andres@correo.com');
INSERT INTO `users` (username, password, enabled, email) VALUES ('admin', '$2a$10$dPlyFSQjGIx1CJzwh5cIt.GTt6XbdYQ6Zlo.u5QGTjNhzMqgYcPRm', 1, 'admin@correo.com');

INSERT INTO `authorities` (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO `authorities` (user_id, authority) VALUES (2, 'ROLE_ADMIN');
INSERT INTO `authorities` (user_id, authority) VALUES (2, 'ROLE_USER');