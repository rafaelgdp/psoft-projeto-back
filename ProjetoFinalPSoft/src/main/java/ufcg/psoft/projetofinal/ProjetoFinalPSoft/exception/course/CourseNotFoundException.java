package ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.course;

@SuppressWarnings("serial")
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String msg) {
        super(msg);
    }
}
