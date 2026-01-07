package DataAccess.DTO;

public class EstudianteDTO {
    private Integer PkEstudiante ;    
    private Integer IdTarjeta    ;
    private String  Nombre       ;
    private String  Apellido     ;
    private Integer Edad         ;
    private Integer Sexo         ;
    private Integer Aula         ;
    private String  Estado       ;
    private Integer FechaCreacion;
    private Integer FechaModifica;

    public EstudianteDTO() {
    }
    public EstudianteDTO(Integer pkEstudiante, Integer idTarjeta, String nombre, String apellido, Integer edad,
            Integer sexo, Integer aula, String estado, Integer fechaCreacion, Integer fechaModifica) {
        this.PkEstudiante = pkEstudiante;
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
        return PkEstudiante;
    }
    public void setPkEstudiante(Integer pkEstudiante) {
        this.PkEstudiante = pkEstudiante;
    }
    public Integer getIdTarjeta() {
        return IdTarjeta;
    }
    public void setIdTarjeta(Integer idTarjeta) {
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
    public Integer getFechaCreacion() {
        return FechaCreacion;
    }
    public void setFechaCreacion(Integer fechaCreacion) {
        this.FechaCreacion = fechaCreacion;
    }
    public Integer getFechaModifica() {
        return FechaModifica;
    }
    public void setFechaModifica(Integer fechaModifica) {
        this.FechaModifica = fechaModifica;
    }

    @Override
    public String toString (){
        return "\n" + getClass().getName()
             + "\nPkEstudiante " + getPkEstudiante()
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
