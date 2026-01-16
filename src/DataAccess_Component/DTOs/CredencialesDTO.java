package DataAccess_Component.DTOs;

public class CredencialesDTO {
    private Integer IdCredencial;
    private Integer Dueño;
    private String  Usuario;
    private String  Clave;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;
    
    public CredencialesDTO (){}
    public CredencialesDTO(Integer idCredencial, Integer dueño, String usuario, String clave, String estado,
            String fechaCreacion, String fechaModifica) {
        this.IdCredencial = idCredencial;
        this.Dueño = dueño;
        this.Usuario = usuario;
        this.Clave = clave;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }
    
    public Integer getIdCredencial() {
        return IdCredencial;
    }
    public void setIdCredencial(Integer idCredencial) {
        this.IdCredencial = idCredencial;
    }
    public Integer getDueño() {
        return Dueño;
    }
    public void setDueño(Integer dueño) {
        this.Dueño = dueño;
    }
    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }
    public String getClave() {
        return Clave;
    }
    public void setClave(String clave) {
        this.Clave = clave;
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
             + "\n IdCredencial " + getIdCredencial()
             + "\n Dueño " + getDueño()
             + "\n Usuario " + getUsuario()
             + "\n Clave " + getClave()
             + "\n Estado " + getEstado()
             + "\n FechaCreacion " + getFechaCreacion()
             + "\n FechaModifica " + getFechaModifica();
    }
}
