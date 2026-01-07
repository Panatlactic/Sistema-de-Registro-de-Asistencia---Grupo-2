package DataAccess.DTO;

public class TutorDTO {

    private Integer PkTutor;
    private String NombreTutor;

    public TutorDTO(Integer pkTutor, String nombreTutor) {
        this.PkTutor = pkTutor;
        this.NombreTutor = nombreTutor;
    }
    public TutorDTO (){}

    public Integer getPkTutor() {
        return PkTutor;
    }
    public void setPkTutor(Integer pkTutor) {
        this.PkTutor = pkTutor;
    }
    public String getNombreTutor() {
        return NombreTutor;
    }
    public void setNombreTutor(String nombreTutor) {
        this.NombreTutor = nombreTutor;
    }
    
    @Override
    public String toString() {
        return "\n" + getClass().getName()
             + "\n PkTutor:    " + getPkTutor()
             + "\n NombreTutor: " + getNombreTutor();
    }
}
