CREATE DATABASE IF NOT EXISTS car;
USE car;


DROP TABLE IF EXISTS mensajes;
DROP TABLE IF EXISTS reservas;
DROP TABLE IF EXISTS viajes;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS notificaciones;
CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(255),
                          email VARCHAR(255) UNIQUE,
                          email_verificado TIMESTAMP NULL,
                          password VARCHAR(255),
                          token_recordar VARCHAR(100),
                          edad INT,
                          creado_en TIMESTAMP NULL,
                          actualizado_en TIMESTAMP NULL
);


CREATE TABLE viajes (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        usuario_id BIGINT,
                        plazas_disponibles INT DEFAULT 1,
                        origen VARCHAR(255),
                        destino VARCHAR(255),
                        fecha_salida DATETIME,
                        precio DECIMAL(8,2),
                        creado_en TIMESTAMP NULL,
                        actualizado_en TIMESTAMP NULL,
                        FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);


CREATE TABLE reservas (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          viaje_id BIGINT,
                          usuario_id BIGINT,
                          estado VARCHAR(50),
                          creado_en TIMESTAMP NULL,
                          actualizado_en TIMESTAMP NULL,
                          FOREIGN KEY (viaje_id) REFERENCES viajes(id) ON DELETE CASCADE,
                          FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);


CREATE TABLE mensajes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          emisor_id BIGINT,
                          receptor_id BIGINT,
                          viaje_id BIGINT,
                          contenido TEXT,
                          leido BOOLEAN DEFAULT FALSE,
                          creado_en TIMESTAMP NULL,
                          actualizado_en TIMESTAMP NULL,
                          FOREIGN KEY (emisor_id) REFERENCES usuarios(id) ON DELETE CASCADE,
                          FOREIGN KEY (receptor_id) REFERENCES usuarios(id) ON DELETE CASCADE,
                          FOREIGN KEY (viaje_id) REFERENCES viajes(id) ON DELETE SET NULL
);


CREATE TABLE notificaciones (
                                id VARCHAR(36) PRIMARY KEY,
                                tipo VARCHAR(255),
                                notificable_id BIGINT,
                                notificable_tipo VARCHAR(255),
                                datos TEXT,
                                leido_en TIMESTAMP NULL,
                                creado_en TIMESTAMP NULL,
                                actualizado_en TIMESTAMP NULL
);


