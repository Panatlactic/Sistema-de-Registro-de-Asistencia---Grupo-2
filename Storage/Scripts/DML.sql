DELETE FROM Estudiante;
DELETE FROM Credenciales;
DELETE FROM Curso;
DELETE FROM Tutor;
DELETE FROM Sexo;

INSERT INTO
    Sexo (Descripcion)
VALUES
    ("Masculino"),
    ("Femenino")
;

INSERT INTO
    Tutor (NombreTutor)
VALUES
    ("Lic. Liliana"),
    ("Lic. Maria"),
    ("Lic. Fernando"),
    ("Lic. Francisco"),
    ("Lic. Susana")
;

INSERT INTO
    Credenciales (Dueño, Usuario, Clave)
VALUES
    (1, "Liliana_69", "9457863")
    (2, "Maria_123", "1234567"),
    (3, "Fernando_456", "7654321"),
    (4, "Francisco_789", "1122334"),
    (5, "Susana_321", "4433221")
    ;

INSERT INTO
    Curso (NombreGrado, DocenteTutor)
VALUES
    ("Noveno Grado", 5),
    ("Decimo Grado", 3),
    ("Primero de Bachillerato", 2),
    ("Segundo de Bachillerato", 4),
    ("Tercero de Bachillerato", 1)
;

-- Viewer
DROP VIEW IF EXISTS ViewEstudiantes;

CREATE VIEW ViewEstudiantes AS
SELECT
    E.IdEstudiante,
    E.IdTarjeta,
    E.Nombre,
    E.Apellido,
    E.Cedula,
    E.Edad,
    S.IdSexo AS Sexo,
    C.IdCurso AS Curso,
    E.Estado,
    E.FechaCreacion,
    E.FechaModifica
FROM Estudiante         E
JOIN Sexo               S ON E.Sexo = S.IdSexo
JOIN Curso              C ON E.Aula = C.IdCurso
WHERE E.Estado = 'A';