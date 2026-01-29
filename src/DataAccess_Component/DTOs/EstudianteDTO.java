package DataAccess_Component.DTOs;

/**
 * DTO para la entidad Estudiante.
 * Los nombres de campos deben coincidir EXACTAMENTE con las columnas de la BD.
 */
public class EstudianteDTO {
    private Integer IdEstudiante;
    private String IdTarjeta;
    private String Nombre;
    private String Apellido;
    private String Cedula;
    private Integer Edad;
    private Integer Sexo;
    private Integer IdCurso; // Coincide con la columna de la BD
    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;

    public EstudianteDTO() {
    }

    public EstudianteDTO(Integer idEstudiante, String idTarjeta, String nombre, String apellido, String cedula,
            Integer edad, Integer sexo, Integer idCurso, String estado, String fechaCreacion, String fechaModifica) {
        this.IdEstudiante = idEstudiante;
        this.IdTarjeta = idTarjeta;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Cedula = cedula;
        this.Edad = edad;
        this.Sexo = sexo;
        this.IdCurso = idCurso;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        this.Cedula = cedula;
    }

    public Integer getIdEstudiante() {
        return IdEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.IdEstudiante = idEstudiante;
    }

    public String getIdTarjeta() {
        return IdTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.IdTarjeta = idTarjeta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer edad) {
        this.Edad = edad;
    }

    public Integer getSexo() {
        return Sexo;
    }

    public void setSexo(Integer sexo) {
        this.Sexo = sexo;
    }

    // Getter/Setter para IdCurso (compatibilidad con getAula/setAula)
    public Integer getIdCurso() {
        return IdCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.IdCurso = idCurso;
    }

    // Alias para compatibilidad con código existente
    public Integer getAula() {
        return IdCurso;
    }

    public void setAula(Integer aula) {
        this.IdCurso = aula;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.FechaCreacion = fechaCreacion;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        this.FechaModifica = fechaModifica;
    }

    @Override
    public String toString() {
        return "\n" + getClass().getName()
                + "\nIdEstudiante: " + getIdEstudiante()
                + "\nIdTarjeta: " + getIdTarjeta()
                + "\nNombre: " + getNombre()
                + "\nApellido: " + getApellido()
                + "\nCedula: " + getCedula()
                + "\nEdad: " + getEdad()
                + "\nSexo: " + getSexo()
                + "\nIdCurso: " + getIdCurso()
                + "\nEstado: " + getEstado()
                + "\nFechaCreacion: " + getFechaCreacion()
                + "\nFechaModifica: " + getFechaModifica();
    }
}
