-- =========================
-- TABLA DE USUARIOS
-- =========================
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL -- admin, viewer
);

-- =========================
-- TABLA DE ANIMES
-- =========================
CREATE TABLE IF NOT EXISTS animes (
    id SERIAL PRIMARY KEY,
    titulo_original VARCHAR(200) NOT NULL,
    titulo_ingles VARCHAR(200),
    titulo_espanol VARCHAR(200),
    descripcion TEXT,
    anio_estreno NUMERIC(4),
    imagen_portada VARCHAR(255) -- ruta o URL
);

-- =========================
-- TABLA DE GENEROS
-- =========================
CREATE TABLE IF NOT EXISTS generos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL
);

-- Relación muchos a muchos: un anime puede tener varios géneros
CREATE TABLE IF NOT EXISTS anime_genero (
    anime_id INT REFERENCES animes(id) ON DELETE CASCADE,
    genero_id INT REFERENCES generos(id) ON DELETE CASCADE,
    PRIMARY KEY (anime_id, genero_id)
);

-- =========================
-- TABLA DE TEMPORADAS
-- =========================
CREATE TABLE IF NOT EXISTS temporadas (
    id SERIAL PRIMARY KEY,
    anime_id INT REFERENCES animes(id) ON DELETE CASCADE,
    numero INT NOT NULL,
    titulo VARCHAR(200)
);

-- =========================
-- TABLA DE CAPITULOS
-- =========================
CREATE TABLE IF NOT EXISTS capitulos (
    id SERIAL PRIMARY KEY,
    temporada_id INT REFERENCES temporadas(id) ON DELETE CASCADE,
    numero INT NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    duracion_minutos INT,
    archivo_video VARCHAR(255) -- ruta local
);
