package DataAccess_Component.DTOs;

public class SexoDTO {

    private Integer IdSexo;
    private String  Descripcion;
    private String  Estado       ;
    private Integer FechaCreacion;
    private Integer FechaModifica;

    public SexoDTO () {}
    public SexoDTO(Integer idSexo, String descripcion, String estado, Integer fechaCreacion, Integer fechaModifica) {
        this.IdSexo = idSexo;
        this.Descripcion = descripcion;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdSexo() {
        return IdSexo;
    }
    public void setIdSexo(Integer IdSexo) {
        this.IdSexo = IdSexo;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
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
    public String toString () {
        return "\n" + getClass().getName()
             + "\n IdSexo " + getIdSexo()
             + "\n Descripcion " + getDescripcion()
             + "\n Estado " + getEstado()
             + "\n FechaCreacion " + getFechaCreacion()
             + "\n FechaModifica " + getFechaModifica();
    }
    
}
