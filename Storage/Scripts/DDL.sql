DROP TABLE IF EXISTS Estudiante;
DROP TABLE IF EXISTS Curso;
DROP TABLE IF EXISTS Credenciales;
DROP TABLE IF EXISTS Tutor;
DROP TABLE IF EXISTS Sexo;

CREATE TABLE Sexo (
    IdSexo         INTEGER PRIMARY KEY AUTOINCREMENT,
    Descripcion    VARCHAR(20) NOT NULL,
    Estado          VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);

CREATE TABLE Tutor (
    IdTutor        INTEGER PRIMARY KEY AUTOINCREMENT,
    NombreTutor    VARCHAR(50) NOT NULL,
    Estado          VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);

CREATE TABLE Credenciales (
    IdCredencial   INTEGER PRIMARY KEY AUTOINCREMENT,
    Dueño          INTEGER NOT NULL REFERENCES Tutor(IdTutor),
    Usuario        VARCHAR(20) NOT NULL UNIQUE,
    Clave          VARCHAR(20) NOT NULL UNIQUE,
    Estado          VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);

CREATE TABLE Curso (
    IdCurso        INTEGER PRIMARY KEY AUTOINCREMENT,
    NombreGrado    VARCHAR(30) NOT NULL,
    DocenteTutor   INTEGER NOT NULL UNIQUE REFERENCES Tutor (IdTutor),
    Estado          VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);

CREATE TABLE Estudiante (
    IdEstudiante    INTEGER PRIMARY KEY AUTOINCREMENT,
    IdTarjeta       INTEGER NOT NULL UNIQUE,
    Nombre          VARCHAR(50) NOT NULL,
    Apellido        VARCHAR(50) NOT NULL,
    Edad            INTEGER NOT NULL,
    Sexo            INTEGER NOT NULL REFERENCES Sexo (IdSexo),
    Aula            INTEGER NOT NULL REFERENCES Curso (IdCurso),
    Estado          VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);