package DataAccess.DTO;

public class SexoDTO {

    private Integer PkSexo;
    private String Descripcion;

    public SexoDTO () {}
    public SexoDTO(Integer pkSexo, String descripcion) {
        this.PkSexo = pkSexo;
        this.Descripcion = descripcion;
    }

    public Integer getPkSexo() {
        return PkSexo;
    }
    public void setPkSexo(Integer pkSexo) {
        this.PkSexo = pkSexo;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    @Override
    public String toString () {
        return "\n" + getClass().getName()
        + "\n PkSexo " + getPkSexo()
        + "\n Descripcion " + getDescripcion();
    }
    
}
