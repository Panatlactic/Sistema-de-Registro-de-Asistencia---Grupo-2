package DataAccess_Component.DTOs;

public class TutorDTO {

    private Integer IdTutor;
    private String  NombreTutor;
    private String  Estado       ;
    private String  FechaCreacion;
    private String  FechaModifica;

    public TutorDTO () {}
    public TutorDTO(Integer idTutor, String nombreTutor, String estado, String fechaCreacion, String fechaModifica) {
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
             + "\n IdTutor:    " + getIdTutor()
             + "\n NombreTutor: " + getNombreTutor()
             + "\n Estado " + getEstado()
             + "\n FechaCreacion " + getFechaCreacion()
             + "\n FechaModifica " + getFechaModifica();
    }
}
