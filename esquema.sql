-- Tabla de usuarios
CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nick VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    fecha_nac DATE NOT NULL
);

-- Tabla de juegos
CREATE TABLE juego (
    id_juego INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    descripcion TEXT
);

-- Tabla del historial de partidas jugadas
CREATE TABLE historial_games (
    id_game INT PRIMARY KEY AUTO_INCREMENT,
    id_jugador1 INT NOT NULL,
    id_jugador2 INT NOT NULL,
    id_juego INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    puntos_j1 INT NOT NULL,
    puntos_j2 INT NOT NULL,
    winner TINYINT, -- 1: jugador1, 2: jugador2, 0: empate, NULL: sin determinar

    FOREIGN KEY (id_jugador1) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_jugador2) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_juego) REFERENCES juego(id_juego)
);

-- Tabla de ranking por usuario y juego
CREATE TABLE ranking (
    id_usuario INT NOT NULL,
    id_juego INT NOT NULL,
    puntos INT NOT NULL DEFAULT 0,

    PRIMARY KEY (id_usuario, id_juego),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_juego) REFERENCES juego(id_juego)
);
