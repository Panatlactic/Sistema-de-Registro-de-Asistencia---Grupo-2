package DataAccess_Component.DTOs;

public class TutorDTO {

    private Integer IdTutor;
    private String  NombreTutor;
    private String  Estado       ;
    private Integer FechaCreacion;
    private Integer FechaModifica;

    public TutorDTO () {}
    public TutorDTO(Integer idTutor, String nombreTutor, String estado, Integer fechaCreacion, Integer fechaModifica) {
        this.IdTutor = idTutor;
        this.NombreTutor = nombreTutor;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdTutor() {
        return IdTutor;
    }
    public void setIdTutor(Integer IdTutor) {
        this.IdTutor = IdTutor;
    }
    public String getNombreTutor() {
        return NombreTutor;
    }
    public void setNombreTutor(String nombreTutor) {
        this.NombreTutor = nombreTutor;
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
             + "\n IdTutor:    " + getIdTutor()
             + "\n NombreTutor: " + getNombreTutor()
             + "\n Estado " + getEstado()
             + "\n FechaCreacion " + getFechaCreacion()
             + "\n FechaModifica " + getFechaModifica();
    }
}
