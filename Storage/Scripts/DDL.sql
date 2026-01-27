DROP TABLE IF EXISTS Estudiante;
DROP TABLE IF EXISTS Curso;
DROP TABLE IF EXISTS Paralelo;
DROP TABLE IF EXISTS Tutor;
DROP TABLE IF EXISTS Credencial;
DROP TABLE IF EXISTS Sexo;

-- Activar claves foráneas
PRAGMA foreign_keys = ON;

CREATE TABLE Sexo (
    IdSexo         INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre         VARCHAR(15)  NOT NULL UNIQUE,
    Descripcion    VARCHAR(20) NOT NULL,
    Estado         VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);

CREATE TABLE Credencial (
    IdCredencial   INTEGER PRIMARY KEY AUTOINCREMENT,
    Usuario        VARCHAR(20) NOT NULL UNIQUE,
    Clave          VARCHAR(20) NOT NULL UNIQUE,
    Estado         VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);

CREATE TABLE Tutor (
    IdTutor        INTEGER PRIMARY KEY AUTOINCREMENT,
    IdSexo         INTEGER NOT NULL REFERENCES Sexo(IdSexo),
    IdCredencial   INTEGER NOT NULL UNIQUE REFERENCES Credencial(IdCredencial),
    Nombre         VARCHAR(15) NOT NULL,
    Apellido       VARCHAR(15) NOT NULL,
    Estado         VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);

CREATE TABLE Paralelo (
    IdParalelo     INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre         VARCHAR(1)  NOT NULL UNIQUE,
    Estado         VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);

CREATE TABLE Curso (
    IdCurso        INTEGER PRIMARY KEY AUTOINCREMENT,
    IdParalelo     INTEGER NOT NULL REFERENCES Paralelo(IdParalelo),
    IdTutor       INTEGER NOT NULL UNIQUE REFERENCES Tutor (IdTutor),
    Nombre         VARCHAR(30) NOT NULL UNIQUE,
    Estado         VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica  DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);


CREATE TABLE Estudiante (
    IdEstudiante    INTEGER PRIMARY KEY AUTOINCREMENT,
    IdTarjeta       VARCHAR(50) NOT NULL UNIQUE,
    Nombre          VARCHAR(15) NOT NULL,
    Apellido        VARCHAR(15) NOT NULL,
    Cedula          VARCHAR(10) NOT NULL UNIQUE,
    Edad            INTEGER NOT NULL,
    Sexo            INTEGER NOT NULL REFERENCES Sexo (IdSexo),
    IdCurso            INTEGER NOT NULL REFERENCES Curso (IdCurso),
    Estado          VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);