package DataAccess.DTO;

public class CursoDTO {
    
    private Integer PkCurso;
    private String NombreGrado;
    private Integer DocenteTutor;

    public CursoDTO() {}
    public CursoDTO(Integer pkCurso, String nombreGrado, Integer docenteTutor) {
        this.PkCurso = pkCurso;
        this.NombreGrado = nombreGrado;
        this.DocenteTutor = docenteTutor;
    }

    public Integer getPkCurso() {
        return PkCurso;
    }
    public void setPkCurso(Integer pkCurso) {
        this.PkCurso = pkCurso;
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

    @Override
    public String toString() {
        return "\n" + getClass().getName()
             + "\n PkCurso " + getPkCurso()
             + "\n NombreGrado " + getNombreGrado()
             + "\n DocenteTutor " + getDocenteTutor();
    }

}
