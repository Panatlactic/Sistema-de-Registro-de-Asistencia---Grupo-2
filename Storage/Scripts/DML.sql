DELETE FROM Estudiante;
DELETE FROM Curso;
DELETE FROM Paralelo;
DELETE FROM Tutor;
DELETE FROM Credencial;
DELETE FROM Sexo;


INSERT INTO Sexo(Nombre, Descripcion) VALUES
                ('M',     'Masculino'),
                ('F',     'Femenino');

INSERT INTO Credencial  (Usuario,           Clave    ) VALUES
                        ('Liliana_69',      '9457863'),
                        ('Maria_123',       '1234567'),
                        ('Fernando_456',    '7654321'),
                        ('Francisco_789',   '1122334'),
                        ('Susana_321',      '4433221');

INSERT INTO Tutor (Nombre, Apellido, IdSexo, IdCredencial) VALUES
                ('Liliana', 'Perez', 2, 1),
                ('Maria', 'Gomez', 2, 2),
                ('Fernando', 'Lopez', 1, 3),
                ('Francisco', 'Diaz', 1, 4),
                ('Susana', 'Martinez', 2, 5);


INSERT INTO Paralelo (Nombre) VALUES
    ('A'),
    ('B'),
    ('C');

INSERT INTO Curso (Nombre, IdParalelo, IdTutor) VALUES
    ('Noveno Grado', 1, 5),
    ('Decimo Grado', 2, 3),
    ('Primero de Bachillerato', 3, 2),
    ('Segundo de Bachillerato', 1, 4),
    ('Tercero de Bachillerato', 2, 1);

INSERT INTO Estudiante (IdTarjeta, Nombre, Apellido, Cedula, Edad, Sexo, IdCurso) VALUES
    ('TARJ001', 'Juan', 'Perez', '0102030405', 14, 1, 1),
    ('TARJ002', 'Maria', 'Lopez', '0203040506', 15, 2, 2),
    ('TARJ003', 'Carlos', 'Gomez', '0304050607', 16, 1, 3),
    ('TARJ004', 'Ana', 'Diaz', '0405060708', 14, 2, 1),
    ('TARJ005', 'Luis', 'Martinez', '0506070809', 17, 1, 5);



DROP VIEW IF EXISTS ViewEstudiantes;

CREATE VIEW ViewEstudiantes AS
SELECT
    E.IdEstudiante,
    E.IdTarjeta,
    E.Nombre,
    E.Apellido,
    E.Cedula,
    E.Edad,
    S.Nombre AS Sexo,
    C.Nombre AS Curso,
    E.Estado,
    E.FechaCreacion,
    E.FechaModifica
FROM Estudiante E
JOIN Sexo S ON E.Sexo = S.IdSexo
JOIN Curso C ON E.IdCurso = C.IdCurso
WHERE E.Estado = 'A';

SELECT * FROM ViewEstudiantes;

DROP VIEW IF EXISTS vwCursos;

CREATE VIEW vwCursos AS
SELECT
    C.IdCurso,
    C.Nombre AS Curso,
    P.Nombre AS Paralelo,
    T.Nombre || ' ' || T.Apellido AS Tutor,
    CR.Usuario AS UsuarioTutor,
    C.Estado,
    C.FechaCreacion,
    C.FechaModifica
FROM Curso C
JOIN Paralelo P ON C.IdParalelo = P.IdParalelo
JOIN Tutor T ON C.IdTutor = T.IdTutor
JOIN Credencial CR ON T.IdCredencial = CR.IdCredencial
WHERE C.Estado = 'A';

SELECT * FROM vwCursos;


