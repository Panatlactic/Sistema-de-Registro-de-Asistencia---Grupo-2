package DataAccess_Component.DTOs;

public class SexoDTO {

    private Integer IdSexo;
    private String  Descripcion;
    private String  Estado       ;
    private String  FechaCreacion;
    private String  FechaModifica;

    public SexoDTO () {}
    public SexoDTO(Integer idSexo, String descripcion, String estado, String fechaCreacion, String fechaModifica) {
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
    public String toString () {
        return "\n" + getClass().getName()
             + "\n IdSexo " + getIdSexo()
             + "\n Descripcion " + getDescripcion()
             + "\n Estado " + getEstado()
             + "\n FechaCreacion " + getFechaCreacion()
             + "\n FechaModifica " + getFechaModifica();
    }
    
}
