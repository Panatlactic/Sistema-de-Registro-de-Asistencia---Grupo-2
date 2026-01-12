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