DROP TABLE IF EXISTS Estudiante;
DROP TABLE IF EXISTS Curso;
DROP TABLE IF EXISTS Tutor;
DROP TABLE IF EXISTS Sexo;

CREATE TABLE Sexo (
    PkSexo         INTEGER PRIMARY KEY AUTOINCREMENT,
    Descripcion    VARCHAR(20) NOT NULL
);

CREATE TABLE Tutor (
    PkTutor        INTEGER PRIMARY KEY AUTOINCREMENT,
    NombreTutor    VARCHAR(50) NOT NULL
);

CREATE TABLE Curso (
    PkCurso        INTEGER PRIMARY KEY AUTOINCREMENT,
    NombreGrado    VARCHAR(30) NOT NULL,
    DocenteTutor   INTEGER NOT NULL UNIQUE REFERENCES Tutor (PkTutor)
);

CREATE TABLE Estudiante (
    PkEstudiante    INTEGER PRIMARY KEY AUTOINCREMENT,
    IdTarjeta       INTEGER NOT NULL UNIQUE,
    Nombre          VARCHAR(50) NOT NULL,
    Apellido        VARCHAR(50) NOT NULL,
    Edad            INTEGER NOT NULL,
    Sexo            INTEGER NOT NULL REFERENCES Sexo (PkSexo),
    Aula            INTEGER NOT NULL REFERENCES Curso (PkCurso),
    Estado          VARCHAR(1) NOT NULL DEFAULT ('A'),
    FechaCreacion   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime')),
    FechaModifica   DATETIME NOT NULL DEFAULT(datetime('now', 'localtime'))
);