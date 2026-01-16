package DataAccess_Component.DTOs;

public class EstudianteDTO {
    private Integer IdEstudiante ;    
    private String IdTarjeta     ;
    private String  Nombre       ;
    private String  Apellido     ;
    private Integer Edad         ;
    private Integer Sexo         ;
    private Integer Aula         ;
    private String  Estado       ;
    private String  FechaCreacion;
    private String  FechaModifica;

    public EstudianteDTO() {
    }
    public EstudianteDTO(Integer idEstudiante, String idTarjeta, String nombre, String apellido, Integer edad,
            Integer sexo, Integer aula, String estado, String fechaCreacion, String fechaModifica) {
        this.IdEstudiante = idEstudiante;
        this.IdTarjeta = idTarjeta;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Edad = edad;
        this.Sexo = sexo;
        this.Aula = aula;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }
    public Integer getPkEstudiante() {
        return IdEstudiante;
    }
    public void setPkEstudiante(Integer idEstudiante) {
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
    public Integer getAula() {
        return Aula;
    }
    public void setAula(Integer aula) {
        this.Aula = aula;
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
    public String toString (){
        return "\n" + getClass().getName()
             + "\nIdEstudiante " + getPkEstudiante()
             + "\nIdTarjeta " + getIdTarjeta()
             + "\nNombre " + getNombre()
             + "\nApellido " + getApellido()
             + "\nEdad " + getEdad()
             + "\nSexo" + getSexo()
             + "\nAula " + getAula()
             + "\nEstado " + getEstado()
             + "\nFechaCreacion " + getFechaCreacion()
             + "\nFechaModifica " + getFechaModifica();
    }
}
