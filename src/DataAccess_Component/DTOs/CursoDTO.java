package DataAccess_Component.DTOs;

public class CursoDTO {
    
    private Integer IdCurso;
    private String  NombreGrado;
    private Integer DocenteTutor;
    private String  Estado       ;
    private Integer FechaCreacion;
    private Integer FechaModifica;

    public CursoDTO() {}
    public CursoDTO(Integer idCurso, String nombreGrado, Integer docenteTutor, String estado, Integer fechaCreacion,
            Integer fechaModifica) {
        this.IdCurso = idCurso;
        this.NombreGrado = nombreGrado;
        this.DocenteTutor = docenteTutor;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdCurso() {
        return IdCurso;
    }
    public void setIdCurso(Integer IdCurso) {
        this.IdCurso = IdCurso;
    }
    public String getNombreGrado() {
        return NombreGrado;
    }
    public void setNombreGrado(String nombreGrado) {
        this.NombreGrado = nombreGrado;
    }
    public Integer getDocenteTutor() {
        return DocenteTutor;
    }
    public void setDocenteTutor(Integer docenteTutor) {
        this.DocenteTutor = docenteTutor;
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
    public String toString() {
        return "\n" + getClass().getName()
             + "\n IdCurso " + getIdCurso()
             + "\n NombreGrado " + getNombreGrado()
             + "\n DocenteTutor " + getDocenteTutor()
             + "\n Estado " + getEstado()
             + "\n FechaCreacion " + getFechaCreacion()
             + "\n FechaModifica " + getFechaModifica();
    }

}
