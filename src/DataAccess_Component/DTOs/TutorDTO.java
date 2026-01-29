package DataAccess_Component.DTOs;

/**
 * DTO para la tabla Tutor.
 * Campos deben coincidir con las columnas de la BD.
 */
public class TutorDTO {
    private Integer IdTutor;
    private Integer IdSexo;
    private Integer IdCredencial;
    private String Nombre;
    private String Apellido;
    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;

    public TutorDTO() {}

    public TutorDTO(Integer idTutor, Integer idSexo, Integer idCredencial, String nombre, 
            String apellido, String estado, String fechaCreacion, String fechaModifica) {
        this.IdTutor = idTutor;
        this.IdSexo = idSexo;
        this.IdCredencial = idCredencial;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdTutor() {
        return IdTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.IdTutor = idTutor;
    }

    public Integer getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(Integer idSexo) {
        this.IdSexo = idSexo;
    }

    public Integer getIdCredencial() {
        return IdCredencial;
    }

    public void setIdCredencial(Integer idCredencial) {
        this.IdCredencial = idCredencial;
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

    /**
     * Obtiene el nombre completo del tutor.
     */
    public String getNombreCompleto() {
        String n = (Nombre != null ? Nombre : "");
        String a = (Apellido != null ? Apellido : "");
        return (n + " " + a).trim();
    }

    @Override
    public String toString() {
        return "TutorDTO{" +
                "IdTutor=" + IdTutor +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Estado='" + Estado + '\'' +
                '}';
    }
}
